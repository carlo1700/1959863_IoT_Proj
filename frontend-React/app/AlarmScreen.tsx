import React, { use, useEffect, useState } from 'react';
import { View, Text, StyleSheet, Dimensions, TouchableOpacity, Image , Pressable, Switch} from 'react-native';
import { useRouter } from 'expo-router';
import AsyncStorage from '@react-native-async-storage/async-storage';
import { Ionicons } from '@expo/vector-icons';

import Svg, { Polygon, Defs, Stop, LinearGradient } from 'react-native-svg';
import { LinearGradient as ExpoLinearGradient } from 'expo-linear-gradient';

import { getAlarmStatus, turnOnAlarm, getDevices, getStatusDevice } from '../services/apis';
import { mapStatusDevice } from '@/services/utils';


const lightAzureColor = '#34C8E8';
const azureColor = '#4E4AF2';
const blueColor = '#242C3B';

export default function AlarmScreen() {
  let { width, height } = Dimensions.get('window');
  const router = useRouter();

  const [awayStatus, setAwayStatus] = useState(false);
  const [alarmStatus, setAlarmStatus] = useState(false);
  const [alarmTriggered, setAlarmTriggered] = useState(false);

  useEffect(() => {
    const getAlarmStatus2 = async () => {
      try {
        const status = await getAlarmStatus();
        console.log('Alarm status:', status);
        if (status !== null && status !== undefined && status !== 'Alarm not triggered') {
          setAlarmTriggered(true);
        }
        else {
          setAlarmTriggered(false);
        }
      } catch (error) {
        console.error('Error fetching alarm status:', error);
      }
    };
    getAlarmStatus2();
    const interval = setInterval(() => {
      getAlarmStatus2();
    }, 10000);


    const fetchAwayStatus = async () => {
      try {
        const status = await AsyncStorage.getItem('awayStatus');
        if (status !== null) {
          setAwayStatus(status === 'true');
          if (status === 'true') {
            await checkOnDevices();
          }
        }
      } catch (error) {
        console.error('Error fetching away status:', error);
      }
    };
    fetchAwayStatus();
  }, []);

  const checkOnDevices = async () => {
    const devices = await getDevices();
    let alertMessage = 'Warning!\n';
    for (let i = 0; i < devices.length; i++) {
      let deviceId = devices[i].deviceId;
      let statusDevice = await getStatusDevice(deviceId);
      let array = mapStatusDevice(deviceId, statusDevice);
      switch (array[0]) {
        case 'light':
          if (array[1] === 'On') {
            alertMessage += `${deviceId} is On\n`;
          }
          break;
        case 'oven':
          if (array[1] === 'On') {
            alertMessage += `${deviceId} is On\n`;
          }
          break;
        case 'blind':
          if (array[1] === 'Up') {
            alertMessage += `${deviceId} is Up\n`;
          }
        case 'motionsensor':
          break;
        case 'thermostat':
          break;
        case 'airconditioner':
          if (array[1] === 'On') {
            alertMessage += `${deviceId} is On\n`;
          }
          break;
        case 'dishwasher':
          if (array[1] === 'On') {
            alertMessage += `${deviceId} is On\n`;
          }
          break;
        case 'washingmachine':
          if (array[1] === 'On') {
            alertMessage += `${deviceId} is On\n`;
          }
          break;
        case 'solarpanel':
          break;
        default:
          break;
      }
    }
    if (alertMessage !== 'Warning!\n') {
      alert(alertMessage);
    }
  };

  const handleAwayStatusChange = async (value: boolean) => {
    if (value) {
      await checkOnDevices();
    }
    try {
      await AsyncStorage.setItem('awayStatus', JSON.stringify(value));
      setAwayStatus(value);
    } catch (error) {
      console.error('Error saving away status:', error);
    }
  };

  const handleAlarmStatusChange = async (value: boolean) => {
    try {
      const rest = await turnOnAlarm(value);
      setAlarmStatus(value);
      setAlarmTriggered(false);
      await AsyncStorage.setItem('alarmStatus', JSON.stringify(value));
    } catch (error) {
      alert(`Error saving alarm status: ${error}`);
    }
  };

  return (
    <View style={{ backgroundColor: blueColor, flex: 1, height: height, width: width, alignItems: 'center'}}>
      <View style={[styles.topTextContainer, { marginTop: height * 0.075, alignItems: 'center', marginRight: width * 0.2}]}>
        <Pressable onPress={() => router.back()} style={{ marginRight: 1}}>
          <ExpoLinearGradient colors={['#34C8E8', '#4E4AF2']} start={{ x: 0, y: 0 }} end={{ x: 1, y: 1 }} style={styles.button}>
            <Ionicons name="chevron-back" size={23} color="#fff" style={{ marginRight: 1 }} />
          </ExpoLinearGradient>
        </Pressable>
        <Text style={{ color: '#fff', fontSize: 32, fontWeight: 'bold', marginLeft: width * 0.15, alignItems: 'center'}}>Status</Text>
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
      <View style={{height: height * 0.36, alignItems: 'center', justifyContent: 'center'}}>
        <View style={{justifyContent: 'center', alignItems: 'center', opacity: 0.95, borderRadius: 20, marginTop: height * 0.05, width: width * 0.9, height: height * 0.35}}>
          <ExpoLinearGradient colors={['#353F54', '#222834']} start={{ x: 0, y: 0 }} end={{ x: 1, y: 1 }} style={styles.meteoGradient}>
            <View style={[{ width: width * 0.9, alignItems: 'center', justifyContent: 'center', paddingVertical: 10, }]}>
              <View style={{  width: width * 0.9, paddingHorizontal: 20, alignItems: 'center',}}>
                <Text style={{ color: '#fff', fontSize: 20, fontWeight: 'bold', marginBottom: 16}}>Alarm - {alarmStatus ? 'Active' : 'Inactive'}</Text>
                <View style={{alignItems: 'center', justifyContent: 'center', backgroundColor: alarmStatus? 'rgba(76, 237, 106, 0.12)':'rgba(30, 36, 64, 0.12)', width: width * 0.38, height: width * 0.38, borderRadius: width * 0.38}}>
                <Pressable onPress={() => handleAlarmStatusChange(!alarmStatus)} style={[styles.buttonStatus, { width: width * 0.34, height: width * 0.34, borderRadius: width * 0.34, backgroundColor: alarmStatus ? 'rgba(76, 237, 106, 1)' : 'rgba(30, 36, 64, 0.6)'}]}>
                  <Image source={require('../assets/images/icons/security.png')} style={{ width: width * 0.23, height: width * 0.23, opacity: alarmStatus ? 1 : 0.6}} />
                </Pressable>
                </View>
              </View>
              {alarmTriggered && (
                <Text style={{ color: '#FF4C4C', fontSize: 16, fontWeight: 'bold' }}>
                  Alert! Alarm Triggered!
                </Text>
              )}
            </View>
          </ExpoLinearGradient>
        </View>
      </View>
      <View style={{justifyContent: 'center', alignItems: 'center', opacity: 0.95, borderRadius: 20, marginTop: height * 0.05, width: width * 0.9, height: height * 0.36}}>
        <ExpoLinearGradient colors={['#353F54', '#222834']} start={{ x: 0, y: 0 }} end={{ x: 1, y: 1 }} style={styles.meteoGradient}>
          <View style={[{ width: width * 0.9, alignItems: 'center', paddingVertical: 10,}]}>
            <View style={{  width: width * 0.9, paddingHorizontal: 20, alignItems: 'center',}}>
              <Text style={{ color: '#fff', fontSize: 20, fontWeight: 'bold', marginBottom: 16}}>Away - {awayStatus ? 'Active' : 'Inactive'}</Text>
              <View style={{alignItems: 'center', justifyContent: 'center', backgroundColor: awayStatus? 'rgba(76, 237, 106, 0.12)':'rgba(30, 36, 64, 0.12)', width: width * 0.38, height: width * 0.38, borderRadius: width * 0.38}}>
              <Pressable onPress={() => handleAwayStatusChange(!awayStatus)} style={[styles.buttonStatus, { width: width * 0.34, height: width * 0.34, borderRadius: width * 0.34, backgroundColor: awayStatus ? 'rgba(76, 237, 106, 1)' : 'rgba(30, 36, 64, 0.6)'}]}>
                <Image source={require('../assets/images/icons/security.png')} style={{ width: width * 0.23, height: width * 0.23, opacity: awayStatus ? 1 : 0.6}} />
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
    justifyContent: 'center',
  },
  meteoGradientContent: {
    top: -3,
    justifyContent: 'space-around',
    alignItems: 'center',
    borderRadius: 20,
    flexDirection: 'row',
    paddingVertical: 10
  },
  buttonStatus: {
    justifyContent: 'center',
    alignItems: 'center',
  },
});
