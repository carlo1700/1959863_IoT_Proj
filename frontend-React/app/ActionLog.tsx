import React, { useEffect, useState } from 'react';
import { View, Text, StyleSheet, Dimensions, Image, Pressable, FlatList, Button } from 'react-native';
import { useRouter } from 'expo-router';
import AsyncStorage from '@react-native-async-storage/async-storage';
import { Ionicons } from '@expo/vector-icons';
import Entypo from '@expo/vector-icons/Entypo';
import FontAwesome6 from '@expo/vector-icons/FontAwesome6';

import Svg, { Polygon, Defs, Stop, LinearGradient } from 'react-native-svg';
import { LinearGradient as ExpoLinearGradient } from 'expo-linear-gradient';

import { getRecentLogs } from '../services/apis';


const lightAzureColor = '#34C8E8';
const azureColor = '#4E4AF2';
const blueColor = '#242C3B';

type LogEntry = {
  id: number;
  time: string;
  device: string;
  action: string;
  type?: 'header' | 'log';
};

export default function HomeScreen() {
  const { width, height } = Dimensions.get('window');
  let tabBarHeight = height * 0.15;
  const router = useRouter();
  const maxSlots = 15; // numero massimo di slot per pagina
  const ITEMS_PER_PAGE = 8;

  const [currentPageNum, setCurrentPageNum] = useState(0);

  const [actionList, setActionList] = useState<Array<LogEntry>>([]);

  useEffect(() => {
    const fetchLogs = async () => {
      const respLog = await getRecentLogs(400);
      console.log('Fetched Logs:', respLog);
      const filteredLogs = respLog.filter((log: any) => log.status !== 'PENDING');
      // sort by time desc
      filteredLogs.sort((a: any, b: any) => new Date(b.ts).getTime() - new Date(a.ts).getTime());
      // add headers for each different day
      let lastEntryDay = '';
      const actionListWithHeaders = filteredLogs.reduce((acc: Array<LogEntry>, log: any) => {
        const logDate = new Date(log.ts).toLocaleDateString();
        if (!lastEntryDay || lastEntryDay !== logDate) {
          acc.push({
            id: acc.length,
            time: logDate,
            device: '',
            action: '',
            type: 'header',
          });
        }
        acc.push({
          id: log.id,
          time: new Date(log.ts).toLocaleTimeString([], { hour: '2-digit', minute: '2-digit' }),
          device: log.deviceId,
          action: log.action,
        });
        lastEntryDay = logDate;
        return acc;
      }, []);
      setActionList(actionListWithHeaders);
      console.log('Recent Logs:', actionListWithHeaders);
    };
    fetchLogs();
  }, []);

  const totalPages = Math.ceil(actionList.length / ITEMS_PER_PAGE);
  const paginatedWithHeaders = actionList.slice(
    currentPageNum * ITEMS_PER_PAGE,
    (currentPageNum + 1) * ITEMS_PER_PAGE
  );


  return (
    <View style={{ backgroundColor: blueColor, flex: 1, height: height, width: width, alignItems: 'center'}}>
      <View style={[styles.topTextContainer, { marginTop: height * 0.075, alignItems: 'center', marginRight: width * 0.12}]}>
        <Pressable onPress={() => router.back()} style={{ marginRight: 1}}>
          <ExpoLinearGradient colors={['#34C8E8', '#4E4AF2']} start={{ x: 0, y: 0 }} end={{ x: 1, y: 1 }} style={styles.button}>
            <Ionicons name="chevron-back" size={23} color="#fff" style={{ marginRight: 1 }} />
          </ExpoLinearGradient>
        </Pressable>
        <Text style={{ color: '#fff', fontSize: 32, fontWeight: 'bold', marginLeft: width * 0.1, alignItems: 'center'}}>Actions Log</Text>
      </View>
      <View style={{ position: 'absolute', top: 0, zIndex: -1}}>
        <Svg width={width} height={height}>
          <Defs>
            <LinearGradient id="grad" x1="0" y1="0" x2="1" y2="1">
              <Stop offset="0%" stopColor="#34C8E8" stopOpacity="1" />
              <Stop offset="100%" stopColor="#4E4AF2" stopOpacity="1" />
            </LinearGradient>
          </Defs>
          <Polygon
            points={`${width},0 ${width},0 0,${height} ${width},${height}`}
            fill="url(#grad)"
          />
        </Svg>
      </View>

      <View style={{justifyContent: 'center', alignItems: 'center', opacity: 0.95, borderRadius: 20, marginTop: height * 0.05, width: width * 0.9, height: height * 0.75}}>
        <ExpoLinearGradient colors={['#353F54', '#222834']} start={{ x: 0, y: 0 }} end={{ x: 1, y: 1 }} style={styles.meteoGradient}>
          <View style={[{ width: width * 0.9, alignItems: 'center', paddingVertical: 10,}]}>
            <View style={{ marginVertical: 10, width: width * 0.9, paddingHorizontal: 20, alignItems: 'flex-start', height: height * 0.69}}>
              <FlatList
                data={paginatedWithHeaders}
                keyExtractor={(item, index) => {
                  if (item.type === 'header') return `header-${item.time}-${index}`;
                  return `item-${item.id}`;
                }}
                 renderItem={({ item }) => {
                  if (item.type === 'header') {
                    return (
                      <View style={{alignItems: 'center'}}>
                        <Text style={{ color: '#fff', fontSize: 20, fontWeight: 'bold', textAlign: 'center', opacity: 0.8, marginTop: 6,}}>{item.time}</Text>
                        <View style={{ height: 1, width: '60%', borderColor:'purple', opacity: 0.4, borderWidth: 2, marginVertical: 6,}}></View>
                      </View>
                    );
                  } else {
                    const action = item;
                    return (
                      <View style={{ marginBottom: 10, padding: 10, width: width * 0.8, flexDirection: 'row', justifyContent: 'space-between', alignItems: 'center'}}>
                        <Text style={{ fontSize: 13, color: 'white', width: width * 0.6 }}>
                          {`${action.device} - ${action.action} - ${action.time}`.length > 45
                            ? `${action.device} - ${action.action} - ${action.time}`.slice(0, 45) + '...'
                            : `${action.device} - ${action.action} - ${action.time}`}
                        </Text>
                        <Pressable onPress={() => router.push({pathname: '/ActionDetails', params: {logEntry: JSON.stringify(action)}})} style={{marginLeft: 4, alignItems: 'center', justifyContent: 'center'}}>
                          <Ionicons name="chevron-forward" size={16} color="#fff" style={{ top: 2.2, opacity: 0.9 }} />
                        </Pressable>
                      </View>
                    )}
                  }}
              />
              <View style={{ flexDirection: 'row', justifyContent: 'space-between', width: '100%', marginTop: 20 }}>
                <Pressable
                  onPress={() => setCurrentPageNum((prev) => Math.max(prev - 1, 0))}
                  disabled={currentPageNum === 0}
                  style={({ pressed }) => ({
                    opacity: currentPageNum === 0 ? 0.3 : pressed ? 0.6 : 1,
                    padding: 10,
                  })}
                >
                  <Ionicons name="arrow-back-circle" size={32} color="#007AFF" />
                </Pressable>
                <Text style={{ alignSelf: 'center', color: 'white'}}>Page {currentPageNum + 1} of {totalPages}</Text>
                <Pressable
                  onPress={() => setCurrentPageNum((prev) => Math.min(prev + 1, totalPages - 1))}
                  disabled={currentPageNum + 1 >= totalPages}
                  style={({ pressed }) => ({
                    opacity: currentPageNum + 1 >= totalPages ? 0.3 : pressed ? 0.6 : 1,
                    padding: 10,
                  })}
                >
                  <Ionicons name="arrow-forward-circle" size={32} color="#007AFF" />
                </Pressable>
              </View>
            </View>
          </View>
        </ExpoLinearGradient>
      </View>
    </View>


  );
}

const styles = StyleSheet.create({
  button: {
    width: 36,
    height: 36,
    borderRadius: 12,
    justifyContent: 'center',
    alignItems: 'center',
    shadowColor: '#000',
    shadowOpacity: 0.2,
    shadowOffset: { width: 0, height: 2 },
    shadowRadius: 6,
    elevation: 3,
  },
  gridContainer: {
    position: 'absolute',
    bottom: 0,
    left: 0,
    right: 0,
    flexDirection: 'row',
    justifyContent: 'space-around',
    alignItems: 'center',
  },
  topTextContainer: {
    flexDirection: 'row',
    justifyContent: 'space-between',
    alignItems: 'center',
    paddingHorizontal: 33,
  },
  meteoGradient: {
    flex: 1,
    borderRadius: 20,
    shadowColor: '#000',
    shadowOffset: { width: 0, height: 6 },
    shadowOpacity: 0.3,
    shadowRadius: 10,
    elevation: 8, // per Android
    alignSelf: 'center',
  },
  meteoGradientContent: {
    top: -3,
    justifyContent: 'space-around',
    alignItems: 'center',
    borderRadius: 20,
    flexDirection: 'row',
    paddingVertical: 10
  },
});
