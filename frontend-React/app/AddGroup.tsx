import React, { useEffect, useState } from 'react';
import { View, Text, StyleSheet, Dimensions, Image, Pressable, FlatList, Button, ScrollView, TextInput } from 'react-native';
import { RouteProp, useRoute } from '@react-navigation/native';
import { Ionicons } from '@expo/vector-icons';
import { LinearGradient as ExpoLinearGradient } from 'expo-linear-gradient';
import { useRouter } from 'expo-router';
import MaterialCommunityIcons from '@expo/vector-icons/MaterialCommunityIcons';
import Entypo from '@expo/vector-icons/Entypo';

import { listGroups, createGroup, getDevices, addDeviceToGroup} from '../services/apis';

const lightAzureColor = '#34C8E8';
const azureColor = '#4E4AF2';
const blueColor = '#242C3B';

type Device = {
	id: string;
	type: string;
	name?: string;
}

export default function AddGroup() {
	const { width, height } = Dimensions.get('window');
	const router = useRouter();

	const [isOpenDeviceList, setIsOpenDeviceList] = useState(false);
	const [listOfAllDevices, setListOfAllDevices] = useState<Device[]>([]);
  	const [groupName, setGroupName] = React.useState('New Group');

	const [devicesToAdd, setDevicesToAdd] = useState<Device[]>([]);

	const [blockTouch, setBlockTouch] = useState(false);

	const blockForAWhile = () => {
		setBlockTouch(true);
		setTimeout(() => setBlockTouch(false), 3000);
	};

	useEffect(() => {
		const fetchGroups = async () => {
		try {
			const groups = await listGroups();
			console.log('Fetched groups:', groups);
		} catch (error) {
			alert('Error fetching groups: ' + error);
		}
		};
		const fetchListOfAllDevices = async () => {
			try {
				const devices = await getDevices();
				console.log('Fetched devices:', devices);
				setListOfAllDevices(devices.map ((device: any) => ({ id: device.deviceId, type: device.deviceType })));
			} catch (error) {
				alert('Error fetching devices: ' + error);
			}
		}

		fetchGroups();
		fetchListOfAllDevices();
		}, []);

	async function saveNewGroup() {
		try {
			const newGroupId = await createGroup(groupName);
			await new Promise(resolve => setTimeout(resolve, 100));
			for (const device of devicesToAdd) {
				await addDeviceToGroup(groupName, device.id);
			}
		} catch (error) {
			alert('Error creating group: ' + error);
		}
		alert('Group created successfully!');
		blockForAWhile();
		router.replace('/GroupsScreens');
	}

	function handleDeleteDevice(deviceId: string) {
		const deviceToRemove = devicesToAdd.find((device) => device.id === deviceId);
		if (!deviceToRemove) return;

		setListOfAllDevices((prevDevices) => [...prevDevices, deviceToRemove]);
		setDevicesToAdd((prevDevices) => prevDevices.filter((device) => device.id !== deviceId));
	}

	function handleAddDevice(deviceId: string) {
		const deviceToAdd = listOfAllDevices.find((device) => device.id === deviceId);
		if (!deviceToAdd) return;

		setDevicesToAdd((prevDevices) => [...prevDevices, deviceToAdd]);

		setListOfAllDevices((prevDevices) => prevDevices.filter((device) => device.id !== deviceId));
	}

  return (
	<View style={{backgroundColor: blueColor, flex: 1, overflow: 'hidden'}}>

		{blockTouch && (<View style={styles.overlay} pointerEvents="auto" />)}

		<View style={[styles.topTextContainer, { marginTop: height * 0.075, alignItems: 'center', width: width}]}>
			<Pressable onPress={() => router.back()} style={{ marginRight: 1}}>
			  <ExpoLinearGradient colors={['#34C8E8', '#4E4AF2']} start={{ x: 0, y: 0 }} end={{ x: 1, y: 1 }} style={styles.button}>
				<Ionicons name="chevron-back" size={23} color="#fff" style={{ marginRight: 1 }} />
			  </ExpoLinearGradient>
			</Pressable>
			<Text style={{ color: '#fff', fontSize: 24, fontWeight: 'bold', alignItems: 'center', marginLeft: width * 0.125, width: '100%'}}>Add Group</Text>
		</View>
		<View style={{marginTop: height * 0.02, width: width, alignItems: 'center'}}>
			<ExpoLinearGradient colors={['#363E51', '#4C5770']} start={{x:0, y:0}} end={{x:1, y:1}} style={[styles.button2, {width: width * 0.7, borderRadius: 50}]}>
				<View style={{width: width * 0.55, paddingVertical: height * 0.015, paddingHorizontal: width * 0.007, flexDirection: 'row', alignItems: 'center'}}>
					<TextInput style={{fontSize: 26, fontWeight: 'bold', color: 'white', width: '100%'}} value={groupName} onChangeText={setGroupName} />
					<MaterialCommunityIcons name="lead-pencil" size={26} color="#fff" style={{ marginTop: 3 }}/>
				</View>
			</ExpoLinearGradient>
			<ExpoLinearGradient colors={['#363E51', '#4C5770']} start={{x:0, y:0}} end={{x:2, y:1}} style={[{width: width * 0.6, height: height * 0.07, marginTop: height * 0.04,  zIndex:3}, styles.button2]}>
				<Pressable onPress={() => setIsOpenDeviceList(!isOpenDeviceList)} style={{flexDirection: 'row', alignItems: 'center', paddingHorizontal: 12, width: '100%'}}>
					<Ionicons name="chevron-down" size={22} color="#fff" style={{ marginTop: 3, paddingHorizontal: 12,}} />
					<Text style={{color: '#fff', fontSize: 16, alignItems: 'center'}}>Devices List</Text>
				</Pressable>
			</ExpoLinearGradient>
			{isOpenDeviceList && (
				<View style={{position:'absolute', top: height * 0.21, zIndex:2}}>
					<ExpoLinearGradient colors={['#363E51', '#4C5770']} start={{x:0, y:0}} end={{x:2, y:1}} style={[{width: width * 0.6, top: -height * 0.08, zIndex:1}, styles.button2]}>
					<View style={{height: height * 0.08}}></View>
					<ScrollView style={{width: width * 0.6, height: height * 0.3}}>
						{listOfAllDevices.map((item, index) => (
							<View style={{width: '100%', height: height * 0.06, justifyContent: 'space-between', alignItems: 'center', flexDirection: 'row', paddingHorizontal: width * 0.05}} key={index}>
								<Text style={{color: '#fff', fontSize: 16, alignItems: 'center'}}>{item.id}</Text>
								<Pressable key={index} style={{height: height * 0.06, justifyContent: 'center', alignItems:'center'}} onPress={() => { setIsOpenDeviceList(false); handleAddDevice(item.id); }}>
									<ExpoLinearGradient colors={['#34E882', '#34E882']} start={{x:0, y:0}} end={{x:1, y:1}} style={[styles.button2, {width: width * 0.075, height: width * 0.075, borderRadius: 8}]}>
										<Entypo name="plus" size={28} color="#fff" style={{ marginTop: 1 }}/>
									</ExpoLinearGradient>
								</Pressable>
							</View>
						))}
					</ScrollView>
					</ExpoLinearGradient>
				</View>
			)}
			<ScrollView style={{width: width * 0.88, height: height * 0.35, marginTop: height * 0.05}}>
				 {devicesToAdd.map((device) => (
					<View key={device.id} style={{flexDirection: 'row', alignItems: 'center', justifyContent: 'space-between'}}>
					<Text style={{fontSize: 24, marginVertical: height * 0.025, color: '#fff'}}>{device.id}</Text>
					<Pressable onPress={() => handleDeleteDevice(device.id)}>
						<ExpoLinearGradient colors={['#E83434', '#F24A4A']} start={{x:0, y:0}} end={{x:1, y:1}} style={[styles.button2, {width: width * 0.09, height: width * 0.09, marginTop: 3, borderRadius: 10}]}>
							<Entypo name="cross" size={36} color="#fff" style={{ marginTop: 1, marginRight: 1 }}/>
						</ExpoLinearGradient>
					</Pressable>
					</View>
				))}
			</ScrollView>
			<Pressable onPress={() => saveNewGroup()} style={{ borderTopWidth: 2, borderColor: lightAzureColor, width: width, marginTop: height * 0.04, alignItems: 'center'}}>
				<ExpoLinearGradient colors={['#34C8E8', '#4E4AF2']} start={{ x: 0, y: 0 }} end={{ x: 1, y: 1 }} style={[styles.button2, {width: width * 0.3, height: height * 0.07, marginTop: height * 0.03}]}>
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
    overlay: {
    position: "absolute",
    top: 0,
    left: 0,
    width: Dimensions.get("window").width,
    height: Dimensions.get("window").height,
    backgroundColor: "transparent", // puoi mettere un colore semitrasparente se vuoi effetto "blocco"
  },

});
