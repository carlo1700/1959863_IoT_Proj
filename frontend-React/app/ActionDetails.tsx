import React from 'react';
import { View, Text, StyleSheet, Dimensions, Image, Pressable, FlatList, Button } from 'react-native';
import { RouteProp, useRoute } from '@react-navigation/native';
import { Ionicons } from '@expo/vector-icons';
import { LinearGradient as ExpoLinearGradient } from 'expo-linear-gradient';
import { useRouter } from 'expo-router';

const lightAzureColor = '#34C8E8';
const azureColor = '#4E4AF2';
const blueColor = '#242C3B';

import { getDeviceImage } from '../services/utils';
type LogEntry = {
  id: number;
  time: string;
  device: string;
  action: string;
  type?: 'header' | 'log';
};


// Tipo per i parametri della route
type RouteParams = {
  params: {
    logEntry: string;
  };
};

const ActionDetails = () => {
  const { width, height } = Dimensions.get('window');
  const route = useRoute<RouteProp<RouteParams, 'params'>>();
  const router = useRouter();

  const { logEntry } = route.params;
  const action = JSON.parse(logEntry);
  if (!action) {
	return (
	  <View style={{backgroundColor: blueColor, flex: 1, alignItems: 'center', justifyContent: 'center'}}>
		  <Text style={{color: 'white', fontSize: 32}}>404 Action Not Found</Text>
      <Pressable onPress={() => router.back()} style={{ marginRight: 1}}>
        <Text style={{ color: '#fff', fontSize: 24, fontWeight: 'bold', alignItems: 'center'}}>Go Back</Text>
      </Pressable>
	  </View>
	);
  }
  return (
    <View style={{backgroundColor: blueColor, flex: 1}}>

      <View style={[styles.topTextContainer, { marginTop: height * 0.075, alignItems: 'center', marginRight: width * 0.12}]}>
        <Pressable onPress={() => router.back()} style={{ marginRight: 1}}>
          <ExpoLinearGradient colors={['#34C8E8', '#4E4AF2']} start={{ x: 0, y: 0 }} end={{ x: 1, y: 1 }} style={styles.button}>
            <Ionicons name="chevron-back" size={23} color="#fff" style={{ marginRight: 1 }} />
          </ExpoLinearGradient>
        </Pressable>
        <Text style={{ color: '#fff', fontSize: 32, fontWeight: 'bold', marginLeft: width * 0.1, alignItems: 'center'}}>Action Info</Text>
      </View>

      <View style={{ justifyContent: 'center', alignItems: 'center', marginTop: height * 0.05 }}>
        <Text style={{ color: '#fff', fontSize: 26, fontWeight: 'bold', textAlign: 'center', marginBottom: 16 }}>{action.device}</Text>
            <ExpoLinearGradient colors={['#363E51', '#4C5770']} start={{x:0, y:0}} end={{x:1, y:1}} style={[{width: width * 0.34, height: width * 0.34}, styles.deviceImage]}>
              <Image
                source={getDeviceImage(action.device)}
                style={{ width: width * 0.25, height: width * 0.25, opacity: 0.9 }}
                resizeMode="contain"
              />
            </ExpoLinearGradient>
        <Text style={{ color: '#fff', fontSize: 20, fontWeight: 'bold', textAlign: 'center', marginTop: height * 0.1 }}>{action.time}</Text>
        <Text style={{ color: lightAzureColor, fontSize: 26, fontWeight: 'bold', textAlign: 'center', marginTop: height * 0.05 }}>{action.action}</Text>
      </View>
    </View>
  );
};

const styles = StyleSheet.create({
  topTextContainer: {
    flexDirection: 'row',
    justifyContent: 'space-between',
    alignItems: 'center',
    paddingHorizontal: 33,
  },
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
  deviceImage: {
    borderRadius: 23,
    justifyContent: 'center',
    alignItems: 'center',
    shadowColor: '#000',
    shadowOpacity: 0.2,
    shadowOffset: { width: 0, height: 2 },
    shadowRadius: 6,
    elevation: 3,
  }
});

export default ActionDetails;
