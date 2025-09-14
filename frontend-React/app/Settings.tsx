import React, { useEffect, useState } from 'react';
import { View, Text, StyleSheet, Dimensions, TouchableOpacity, Linking , Pressable, Switch} from 'react-native';
import { useRouter } from 'expo-router';
import AsyncStorage from '@react-native-async-storage/async-storage';
import { Ionicons } from '@expo/vector-icons';
import MaterialIcons from '@expo/vector-icons/MaterialCommunityIcons';
import Feather from '@expo/vector-icons/Feather';

import Svg, { Polygon, Defs, Stop, LinearGradient } from 'react-native-svg';
import { LinearGradient as ExpoLinearGradient } from 'expo-linear-gradient';


const lightAzureColor = '#34C8E8';
const azureColor = '#4E4AF2';
const blueColor = '#242C3B';

export default function Settings() {
  let { width, height } = Dimensions.get('window');
  let tabBarHeight = height * 0.15;
  const router = useRouter();


  const openSettings = () => {
    Linking.openSettings(); // Apre le impostazioni dell'app
  };

  const handleLogout = async () => {
    try {
      await AsyncStorage.removeItem('accToken');
      await AsyncStorage.removeItem('userSurname');
      await AsyncStorage.removeItem('userName');
      await AsyncStorage.removeItem('key');
      await AsyncStorage.removeItem('exipres');
      await AsyncStorage.removeItem('email');
      await AsyncStorage.removeItem('password');
      await AsyncStorage.removeItem('otpCode');
      await AsyncStorage.removeItem('rememberMe');
      await AsyncStorage.removeItem('otpScad');
      await AsyncStorage.removeItem('otpExpiration');
      await AsyncStorage.removeItem('notificationStatus');
      await AsyncStorage.removeItem('awayStatus');
      await AsyncStorage.removeItem('alarmStatus');

      setTimeout(() => {
        router.replace('/LoginScreen');
      }, 100);
    } catch (error) {
      console.error('Errore nel logout:', error);
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
        <Text style={{ color: '#fff', fontSize: 32, fontWeight: 'bold', marginLeft: width * 0.15, alignItems: 'center'}}>Settings</Text>
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
              <View style={{ flexDirection: 'row', width: '100%', justifyContent: 'space-around', alignItems: 'center', marginVertical: 16 }}>
                <Text style={{ color: '#fff', width: '60%', fontSize: 24, fontWeight: 'bold', alignItems: 'center'}}>Notifcations</Text>
                <Pressable onPress={openSettings} style={{marginTop: 5, alignItems: 'center', justifyContent: 'center'}}>
                  <Feather name="external-link" size={32} color="white" />
                </Pressable>
              </View>
              <View style={{ flexDirection: 'row', width: '100%', justifyContent: 'space-around', alignItems: 'center', marginVertical: 16 }}>
                <Text style={{ color: '#fff', width: '60%', fontSize: 24, fontWeight: 'bold', alignItems: 'center'}}>Logout</Text>
                <Pressable onPress={handleLogout} style={{marginTop: 5, alignItems: 'center', justifyContent: 'center'}}>
                  <MaterialIcons name="logout" size={32} color="white" />
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
