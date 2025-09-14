import React, { useEffect, useState } from 'react';
import { View, Text, StyleSheet, Dimensions, Image, Pressable, FlatList, Button, ScrollView, TextInput } from 'react-native';
import { RouteProp, useRoute } from '@react-navigation/native';
import { Ionicons } from '@expo/vector-icons';
import { LinearGradient as ExpoLinearGradient } from 'expo-linear-gradient';
import { useRouter } from 'expo-router';
import MaterialCommunityIcons from '@expo/vector-icons/MaterialCommunityIcons';
import Entypo from '@expo/vector-icons/Entypo';

import { addDeviceToGroup, removeDeviceFromGroup, getDevices, deleteGroup, renameGroup } from '../services/apis';

import { mapStatusString, getDeviceImage, getNames } from '../services/utils';

import { ActivityIndicator } from 'react-native';

const lightAzureColor = '#34C8E8';
const azureColor = '#4E4AF2';
const blueColor = '#242C3B';

type Device = {
  id: string;
  status?: string;
};

type Group = {
  id: string;
  devices?: Device[];
};

// 👇 Definisci la lista delle rotte
type RootStackParamList = {
  GroupSettings: {
    groupId: string;
    groupsTemp: string;
  };
};
export default function GroupSettings() {
	const { width, height } = Dimensions.get('window');
	const router = useRouter();
	const route = useRoute<RouteProp<RootStackParamList, "GroupSettings">>();
	const { groupId, groupsTemp } = route.params;
	const [groups, setGroups] = useState<Group[]>(JSON.parse(groupsTemp));
	const [namesMap, setNamesMap] = useState<{[key: string]: string}>({});

	const group = groups.find((group) => group.id == groupId);
	if (!group) {
		return (
			<View style={{backgroundColor: blueColor, flex: 1, alignItems: 'center', justifyContent: 'center'}}>
				<Text style={{color: lightAzureColor, fontSize: 32}}>404 Group Not Found</Text>
				<Pressable onPress={() => router.back()} style={{ marginRight: 1}}>
					<Text style={{ color: '#fff', fontSize: 24, fontWeight: 'bold', alignItems: 'center'}}>Go Back</Text>
				</Pressable>
			</View>
		);
	}

	const [groupName, setGroupName] = React.useState(group.id);

	const [isOpenDeviceList, setIsOpenDeviceList] = useState(false);

	const [listOfAllDevices, setListOfAllDevices] = useState<Device[]>([]);
	const [currentDevices, setCurrentDevices] = useState<Device[]>(group.devices || []);
	const [loading, setLoading] = useState(true);

	const [blockTouch, setBlockTouch] = useState(false);

	useEffect(() => {
		const fetchListOfAllDevices = async () => {
			try {
				const devices = await getDevices();
				let partDevices = devices.map ((device: any) => ({ id: device.deviceId, type: device.deviceType }));
				partDevices = partDevices.filter((device: Device) => !currentDevices.some((d) => d.id === device.id));
				setListOfAllDevices(partDevices);
			} catch (error) {
				alert('Error fetching devices: ' + error);
			}
			setLoading(false);
			const names = await getNames();
			setNamesMap(names as {[key: string]: string});
		}
		fetchListOfAllDevices();
	}, []);

	const blockForAWhile = () => {
		setBlockTouch(true);
		setTimeout(() => setBlockTouch(false), 3000);
	};

	async function saveChanges() {
		setBlockTouch(true);
		if (!group) {
			alert('Group not found!');
			setBlockTouch(false);
			return;
		}
		for (const device of currentDevices) {
			if (!group.devices?.some((d) => d.id === device.id)) {
				await addDeviceToGroup(groupName, device.id);
			}
		}
		for (const device of group.devices || []) {
			if (!currentDevices.some((d) => d.id === device.id)) {
				await removeDeviceFromGroup(groupName, device.id);
			}
		}
		await renameGroup(groupId, groupName);
		alert('Changes saved successfully!');
		blockForAWhile();
		router.replace('/GroupsScreens');
	}

	async function handleDeleteGroup() {
		setBlockTouch(true);
		await deleteGroup(groupId);
		alert('Group deleted successfully!');
		blockForAWhile();
		router.replace('/GroupsScreens');
	}

	function handleDeleteDevice(deviceId: string) {
		currentDevices.find((device) => device.id === deviceId);
		if (!currentDevices) return;
		setListOfAllDevices((prevDevices) => [...prevDevices, currentDevices.find((device) => device.id === deviceId)!]);
		setCurrentDevices((prevDevices) => prevDevices.filter((device) => device.id !== deviceId));
	}

	function handleAddDevice(deviceId: string, groupId: string) {
		const deviceToAdd = listOfAllDevices.find((device) => device.id === deviceId);
		if (!deviceToAdd) return;

		setCurrentDevices((prevDevices) => [...prevDevices, deviceToAdd]);
		setListOfAllDevices((prevDevices) => prevDevices.filter((device) => device.id !== deviceId));
	}

  return (
	(loading) ? (
		<View style={{backgroundColor: blueColor, flex: 1, alignItems: 'center', justifyContent: 'center'}}>
			<Text style={{ color: '#fff', fontSize: 18 }}>Loading...</Text>
			<ActivityIndicator size="large" color="#fff" style={{ marginTop: 20 }} />
		</View>
	) : (
		<View style={{backgroundColor: blueColor, flex: 1, overflow: 'hidden'}}>

			{blockTouch && (<View style={styles.overlay} pointerEvents="auto" />)}

			<View style={[styles.topTextContainer, { marginTop: height * 0.075, alignItems: 'center', width: width}]}>
				<Pressable onPress={() => router.back()} style={{ marginRight: 1}}>
				<ExpoLinearGradient colors={['#34C8E8', '#4E4AF2']} start={{ x: 0, y: 0 }} end={{ x: 1, y: 1 }} style={styles.button}>
					<Ionicons name="chevron-back" size={23} color="#fff" style={{ marginRight: 1 }} />
				</ExpoLinearGradient>
				</Pressable>
				<Text style={{ color: '#fff', fontSize: 24, fontWeight: 'bold', alignItems: 'center', marginLeft: width * 0.125, width: '100%'}}>Modify Group</Text>
			</View>
			<View style={{marginTop: height * 0.02, width: width, alignItems: 'center'}}>
				{/* <ExpoLinearGradient colors={['#363E51', '#4C5770']} start={{x:0, y:0}} end={{x:1, y:1}} style={[styles.button2, {width: width * 0.7, borderRadius: 50}]}>
					<View style={{width: width * 0.55, paddingVertical: height * 0.015, paddingHorizontal: width * 0.007, flexDirection: 'row', alignItems: 'center'}}>
						<TextInput style={{fontSize: 26, fontWeight: 'bold', color: 'white', width: '100%'}} value={namesMap[groupName] ?? groupName} onChangeText={setGroupName} />
						<MaterialCommunityIcons name="lead-pencil" size={26} color="#fff" style={{ marginTop: 3 }}/>
					</View>
				</ExpoLinearGradient> */}
				<Text style={{fontSize: 26, fontWeight: 'bold', color: 'white', width: width * 0.7, textAlign: 'center'}}>{namesMap[groupName] ?? groupName}</Text>
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
									<Text style={{color: '#fff', fontSize: 16, alignItems: 'center'}}>{namesMap[item.id] ?? item.id}</Text>
									<Pressable key={index} style={{height: height * 0.06, justifyContent: 'center', alignItems:'center'}} onPress={() => { setIsOpenDeviceList(false); handleAddDevice(item.id, group.id); }}>
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
				<ScrollView style={{width: width * 0.88, height: height * 0.3, marginTop: height * 0.05}}>
					{currentDevices?.map((device) => (
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
				<Pressable onPress={() => saveChanges()} style={{ borderTopWidth: 2, borderColor: lightAzureColor, width: width, marginTop: height * 0.04, alignItems: 'center'}}>
					<ExpoLinearGradient colors={['#34C8E8', '#4E4AF2']} start={{ x: 0, y: 0 }} end={{ x: 1, y: 1 }} style={[styles.button2, {width: width * 0.3, height: height * 0.07, marginTop: height * 0.03}]}>
						<Text style={{ color: '#fff', fontSize: 24, fontWeight: 'bold', alignItems: 'center'}}>Save</Text>
					</ExpoLinearGradient>
				</Pressable>
				<Pressable onPress={() => handleDeleteGroup()} style={{ width: width, alignItems: 'center'}}>
					<ExpoLinearGradient colors={['#E83434', '#F24A4A']} start={{ x: 0, y: 0 }} end={{ x: 1, y: 1 }} style={[styles.button2, {width: width * 0.5, height: height * 0.07, marginTop: height * 0.03}]}>
						<Text style={{ color: '#fff', fontSize: 24, fontWeight: 'bold', alignItems: 'center'}}>Delete Group</Text>
					</ExpoLinearGradient>
				</Pressable>
			</View>
		</View>
	  )
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
