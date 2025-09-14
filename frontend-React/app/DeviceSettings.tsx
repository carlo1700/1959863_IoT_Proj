import React, { useEffect } from 'react';
import { View, Text, StyleSheet, Dimensions, Image, Pressable, FlatList, Button, ScrollView, TextInput } from 'react-native';
import { RouteProp, useRoute } from '@react-navigation/native';
import { Ionicons } from '@expo/vector-icons';
import { LinearGradient as ExpoLinearGradient } from 'expo-linear-gradient';
import { useRouter } from 'expo-router';
import MaterialCommunityIcons from '@expo/vector-icons/MaterialCommunityIcons';

import { getDeviceImage, getNames } from '@/services/utils';
import { setDisplayName } from '@/services/apis';

const lightAzureColor = '#34C8E8';
const azureColor = '#4E4AF2';
const blueColor = '#242C3B';


type Device = {
	id: string;
}


type RouteParams = {
  params: {
    deviceId: string;
  };
};

export default function DeviceSettings() {
  const { width, height } = Dimensions.get('window');
  const router = useRouter();
  const route = useRoute<RouteProp<RouteParams>>();
  const { deviceId } = route.params;

  const [deviceName, setDeviceName] = React.useState(deviceId);

  const [namesMap, setNamesMap] = React.useState<{[key: string]: string}>({});

  useEffect(() => {
  const fetchNames = async () => {
    try {
      const names = await getNames();
      setNamesMap(names as {[key: string]: string});
    } catch (error) {
      alert('Error fetching names: ' + error);
    }
  };
  fetchNames();
}, []);

  function saveNameFromTextInput() {
    const saveName = async () => {
      try {
        await setDisplayName(deviceId, deviceName);
        alert('Name saved successfully!');
        router.back();
      } catch (error) {
        alert('Error saving name: ' + error);
      }
    };
    saveName();
  }

  return (
	<View style={{backgroundColor: blueColor, flex: 1, overflow: 'hidden'}}>
		<View style={[styles.topTextContainer, { marginTop: height * 0.075, alignItems: 'center', width: width}]}>
			<Pressable onPress={() => router.back()} style={{ marginRight: 1}}>
			  <ExpoLinearGradient colors={['#34C8E8', '#4E4AF2']} start={{ x: 0, y: 0 }} end={{ x: 1, y: 1 }} style={styles.button}>
				<Ionicons name="chevron-back" size={23} color="#fff" style={{ marginRight: 1 }} />
			  </ExpoLinearGradient>
			</Pressable>
			<Text style={{ color: '#fff', fontSize: 24, fontWeight: 'bold', alignItems: 'center', marginLeft: width * 0.125, width: '100%'}}>Modify Device</Text>
		</View>
		<View style={{marginTop: height * 0.075, width: width, alignItems: 'center'}}>
			<View style={{ flexDirection: 'row', alignItems: 'center', width: width * 0.9, justifyContent: 'center'}}>
				<ExpoLinearGradient colors={['#363E51', '#4C5770']} start={{x:0, y:0}} end={{x:1, y:1}} style={[{width: width * 0.34, height: width * 0.34}, styles.deviceImage]}>
					<Image source={getDeviceImage(deviceId)} style={{ width: width * 0.25, height: width * 0.25, opacity: 0.9 }} resizeMode="contain"/>
				</ExpoLinearGradient>
			</View>
		</View>
		<View style={{marginTop: height * 0.05, width: width, alignItems: 'center'}}>
			<ExpoLinearGradient colors={['#363E51', '#4C5770']} start={{x:0, y:0}} end={{x:1, y:1}} style={[{width: width * 0.7, marginTop: 3}, styles.button2]}>
				<View style={{width: width * 0.5, paddingVertical: height * 0.015, paddingHorizontal: width * 0.015, flexDirection: 'row', alignItems: 'center'}}>
					<TextInput style={{fontSize: 28, fontWeight: 'bold', color: 'white', outlineStyle: 'none', width: '100%'}} value={namesMap[deviceName] ?? deviceName} onChangeText={setDeviceName}/>
					<MaterialCommunityIcons name="lead-pencil" size={28} color="#fff" style={{ marginTop: 3 }}/>
				</View>
			</ExpoLinearGradient>
			<Pressable onPress={() => saveNameFromTextInput()} style={{ marginRight: 1}}>
				<ExpoLinearGradient colors={['#34C8E8', '#4E4AF2']} start={{ x: 0, y: 0 }} end={{ x: 1, y: 1 }} style={[styles.button2, {width: width * 0.4, height: height * 0.07, marginTop: height * 0.05}]}>
					<Text style={{ color: '#fff', fontSize: 24, fontWeight: 'bold', alignItems: 'center'}}>Save</Text>
				</ExpoLinearGradient>
			</Pressable>
		</View>
	</View>
  );
}

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
  button2: {
    borderRadius: 23,
    justifyContent: 'center',
    alignItems: 'center',
    shadowColor: '#000',
    shadowOpacity: 0.25,
    shadowOffset: { width: 0, height: 2 },
    shadowRadius: 6,
    elevation: 3,
  },
  deviceImage: {
    borderRadius: 22,
    justifyContent: 'center',
    alignItems: 'center',
    shadowColor: '#000',
    shadowOpacity: 0.2,
    shadowOffset: { width: 0, height: 2 },
    shadowRadius: 6,
    elevation: 3,
  },

});
