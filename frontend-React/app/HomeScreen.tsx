import React, { useEffect, useState } from 'react';
import { View, Text, StyleSheet, Pressable, Dimensions, TouchableOpacity, Image } from 'react-native';
import { useRouter } from 'expo-router';
import AsyncStorage from '@react-native-async-storage/async-storage';
import { Ionicons } from '@expo/vector-icons';
import Entypo from '@expo/vector-icons/Entypo';
import FontAwesome6 from '@expo/vector-icons/FontAwesome6';
import * as Notifications from 'expo-notifications';

import * as Location from "expo-location";


import Svg, { Circle, Text as SvgText, Polygon, Defs, Stop, LinearGradient } from 'react-native-svg';
import { LinearGradient as ExpoLinearGradient } from 'expo-linear-gradient';

import { getDevices, getRecentLogs, listDisplayNames } from '../services/apis'

const specialColorBlu = '#005381';
const specialColorOrange = '#F37330';

const lightAzureColor = '#34C8E8';
const azureColor = '#4E4AF2';
const blueColor = '#242C3B';

function getWeatherDescription(code: number): string {
  const map: Record<number, string> = {
    0: "Clear sky",
    1: "Mainly clear",
    2: "Partly cloudy",
    3: "Overcast",
    45: "Fog",
    48: "Depositing rime fog",
    51: "Light drizzle",
    53: "Moderate drizzle",
    55: "Dense drizzle",
    61: "Slight rain",
    63: "Moderate rain",
    65: "Heavy rain",
    71: "Slight snow fall",
    73: "Moderate snow fall",
    75: "Heavy snow fall",
    95: "Thunderstorm",
    99: "Thunderstorm with hail",
  };
  return map[code] || "Unknown weather";
}

type LogEntry = {
  id: number;
  time: string;
  device: string;
  action: string;
};

export default function HomeScreen() {
  let { width, height } = Dimensions.get('window');
  let tabBarHeight = height * 0.15;
  const router = useRouter();
  const [userName, setUserName] = useState<string | null>(null);
  const [status, setStatus] = useState<string | null>(null);

  const [currentTemperature, setCurrentTemperature] = useState<number | null>(null);
  const [currentWeather, setCurrentWeather] = useState("");
  const [cityName, setCityName] = useState("");
  const [countryName, setCountryName] = useState("");

  const [actionList, setActionList] = useState<Array<LogEntry>>([]);

  useEffect(() => {
    (async () => {
      // Richiedi permessi di localizzazione
      let { status } = await Location.requestForegroundPermissionsAsync();
      if (status !== "granted") {
        console.log("Permesso posizione negato");
        return;
      }

      // Ottieni coordinate attuali
      let location = await Location.getCurrentPositionAsync({});
      const { latitude, longitude } = location.coords;

      // Chiamata API OpenWeatherMap
      const res = await fetch(
        `https://api.open-meteo.com/v1/forecast?latitude=${latitude}&longitude=${longitude}&current_weather=true`
      );
      const data = await res.json();

      setCurrentTemperature(Math.round(data.current_weather.temperature));
      setCurrentWeather(getWeatherDescription(data.current_weather.weathercode));
      setCityName("Roma");
      setCountryName("Italia");

      console.log("Current Weather:", {
        temperature: currentTemperature,
        weather: currentWeather,
        city: cityName,
        country: countryName,
      });

      const respLog = await getRecentLogs(20);
      const filteredLogs = respLog.filter((log: any) => log.status !== 'PENDING');
      setActionList(
        filteredLogs.map((log: any) => ({
          id: log.id,
          time: new Date(log.ts).toLocaleTimeString([], { hour: '2-digit', minute: '2-digit' }),
          device: log.deviceId,
          action: log.action,
        }))
      );
    })();

    async function checkPermissions() {
        // Chiede allo smartphone qual è lo stato attuale del permesso notifiche per l'app
        const settings = await Notifications.getPermissionsAsync();
        // Salva lo stato (granted, denied, undetermined) nello state React
        setStatus(settings.status);
        await AsyncStorage.setItem('notificationStatus', settings.status);
      }

      async function getUserName() {
        try {
          const name = await AsyncStorage.getItem('userName');
          if (name !== null) {
            setUserName(name);
          }
        } catch (error) {
          console.error('Error fetching user name:', error);
        }
      }

      getUserName();
      checkPermissions();

      // call getDevices from ../services/apis.tsx
      getDevices()
        .then(devices => {
          console.log('Devices:', devices);
        })
        .catch(error => {
          console.error('Error fetching devices:', error);
        });

    }, []);


  return (
    <View style={{ backgroundColor: blueColor, flex: 1, height: height, width: width }}>
      <View style={{ position: 'absolute', top: 0}}>
        <Svg width={width} height={height}>
          <Defs>
            <LinearGradient id="grad" x1="0" y1="0" x2="1" y2="1">
              <Stop offset="0%" stopColor="#34C8E8" stopOpacity="1" />
              <Stop offset="100%" stopColor="#4E4AF2" stopOpacity="1" />
            </LinearGradient>
          </Defs>
          <Polygon
            points={`${width},${height*0.28} ${width * 0.75},${height*0.2} 0,${height} ${width},${height}`}
            fill="url(#grad)"
          />
        </Svg>
      </View>
      <View style={{ position: 'absolute', bottom: 0 }}>
        <Svg width={width} height={tabBarHeight}>
          <Defs>
            <LinearGradient id="tabGradient" x1="0" y1="0" x2="1" y2="0">
              <Stop offset="0%" stopColor="#363E51" stopOpacity="1"/>
              <Stop offset="100%" stopColor="#181C24" stopOpacity="0.8"/>
            </LinearGradient>
          </Defs>
          <Polygon
            points={`0,${tabBarHeight * 0.2} ${width},0 ${width},${tabBarHeight} 0,${tabBarHeight}`}
            fill="url(#tabGradient)"
          />
        </Svg>
        <View style={[styles.gridContainer, { height: tabBarHeight}]}>
          <TouchableOpacity onPress={() => router.push('/AlarmScreen')}>
            <Ionicons name="alarm" size={26} color="#fff" style={{ marginLeft: 1 }}/>
          </TouchableOpacity>

          <TouchableOpacity onPress={() => router.push('/ActionLog')}>
            <Entypo name="database" size={26} color="#fff" style={{ marginLeft: 1 }}/>
          </TouchableOpacity>

          <TouchableOpacity onPress={() => router.push('/GroupsScreens')}>
            <FontAwesome6 name="object-group" size={26} color="#fff" style={{ marginLeft: 1 }}/>
          </TouchableOpacity>

          <TouchableOpacity onPress={() => router.push('/RoomsScreen')}>
            <Ionicons name="home" size={26} color="#fff" style={{ marginLeft: 1 }}/>
          </TouchableOpacity>
        </View>
      </View>
      <View style={[styles.topTextContainer, { marginTop: height * 0.075, zIndex:9 }]}>
        <Text style={{ color: '#fff', fontSize: 21, fontWeight: 'bold' }}>Hi {userName ? userName : '...'}</Text>
        <TouchableOpacity onPress={() => router.push('/Settings')} style={{ marginRight: 1}}>
          <ExpoLinearGradient colors={['#34C8E8', '#4E4AF2']} start={{ x: 0, y: 0 }} end={{ x: 1, y: 1 }} style={styles.button}>
            <Ionicons name="settings-sharp" size={23} color="#fff" style={{ marginLeft: 1 }} />
          </ExpoLinearGradient>
        </TouchableOpacity>
      </View>
      <View style={{height: height * 0.17,  justifyContent: 'center', zIndex:9, alignItems: 'center', opacity: 0.95, borderRadius: 20, marginTop: height * 0.07}}>
        <ExpoLinearGradient colors={['#456AEB', '#222834']} start={{ x: 0, y: 0 }} end={{ x: 1, y: 1 }} style={styles.meteoGradient}>
          <View style={[styles.meteoGradientContent, { width: width * 0.9 }]}>
            <View>
              <Text style={{ color: '#fff', fontSize: 32, fontWeight: 'bold', textAlign: 'center' }}>{cityName ?? ''}</Text>
              <Text style={{ color: '#fff', fontSize: 20, fontWeight: 'bold', opacity: 0.8, top:-3}}>{countryName ?? ''}</Text>
              <View style={{ flexDirection: 'row', alignItems: 'center', marginTop: 3 }}>
                {(currentWeather === 'Clear sky' || currentWeather === 'Mainly clear')
                  ? <Ionicons name="sunny" size={18} color="#fff" style={{ opacity: 0.8 }} />
                  : null}

                {(currentWeather === 'Slight rain' || currentWeather === 'Moderate rain' || currentWeather === 'Heavy rain' || currentWeather === 'Thunderstorm')
                  ? <Ionicons name="rainy" size={18} color="#fff" style={{ opacity: 0.8 }} />
                  : null}

                {(currentWeather === 'Partly cloudy')
                  ? <Ionicons name="cloudy" size={18} color="#fff" style={{ opacity: 0.8 }} />
                  : null}

                {(currentWeather === 'Snowy')
                  ? <Ionicons name="snow" size={18} color="#fff" style={{ opacity: 0.8 }} />
                  : null}

                <Text style={{ color: '#fff', fontSize: 16, fontWeight: 'bold', marginLeft: 5, opacity: 0.8 }}>
                  {currentWeather ?? ''}
                </Text>
              </View>
            </View>
            <View>
              <Text style={{ color: '#fff', fontSize: 42, fontWeight: 'bold', top: -10}}>{currentTemperature}°C</Text>
            </View>
          </View>
        </ExpoLinearGradient>
      </View>
      <View style={{zIndex:9, height: height * 0.32, width:'100%',  justifyContent: 'center', alignItems: 'center', opacity: 0.95, borderRadius: 20, marginTop: height * 0.08}}>
        <ExpoLinearGradient colors={['#353F54', '#222834']} start={{ x: 0, y: 0 }} end={{ x: 1, y: 1 }} style={styles.meteoGradient}>
          <View style={[{ width: width * 0.9, alignItems: 'center', paddingVertical: 10,}]}>
            <Text style={{ color: '#fff', fontSize: 26, fontWeight: 'bold', textAlign: 'center', opacity: 0.8, marginTop: 6,}}>Actions Log</Text>
            <View style={{ height: 1, width: '60%', borderColor:'purple', opacity: 0.4, borderWidth: 2, marginTop: 6,}}></View>
            <View style={{ marginVertical: 10, width: '100%', paddingHorizontal: 20, alignItems: 'center'}}>
              {actionList.slice(0, 4).map((action) => (
                    <Text key={action.id} style={{ color: '#fff', fontSize: 13, opacity: 0.5, marginVertical: 3}}>
                      {(action.device ?? '')} - {(action.action ?? '')} - {(action.time ?? '')}
                    </Text>
              ))}
             <TouchableOpacity onPress={() => router.push('/ActionLog')} style={{ marginTop: 10, flexDirection: 'row', alignItems: 'center'}}>
              <View style={{ flexDirection: 'row', alignItems: 'center' }}>
                <Text style={{ color: '#fff', fontSize: 14, opacity: 0.9, marginVertical: 3}}>
                  See all actions
                </Text>
                <Ionicons name="chevron-forward" size={13} color="#fff" style={{ top: 2.2, opacity: 0.9, marginLeft: 3 }} />
              </View>
            </TouchableOpacity>
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
    width: '100%',

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
