import React, { useEffect, useState } from 'react';
import { View, Text, StyleSheet, Dimensions, Image, Pressable, FlatList, Button, ScrollView, ActivityIndicator } from 'react-native';
import { RouteProp, useRoute } from '@react-navigation/native';
import { Ionicons } from '@expo/vector-icons';
import { LinearGradient as ExpoLinearGradient } from 'expo-linear-gradient';
import { useRouter } from 'expo-router';
import MaterialCommunityIcons from '@expo/vector-icons/MaterialCommunityIcons';


import { getRoomImage, mapStatusString, getDeviceImage } from '../services/utils';
import { getStatusDevice, listRoomDevices, listRooms, turnOffGroup, turnOffRoom, turnOnGroup, turnOnRoom } from '@/services/apis';

const lightAzureColor = '#34C8E8';
const azureColor = '#4E4AF2';
const blueColor = '#242C3B';

type Device = {
	id: string;
	status?: string;
}

type Room = {
  id: string;
  devices?: Device[];
}


// Tipo per i parametri della route
type RouteParams = {
  params: {
    roomId: string;
  };
};

const RoomScreen = () => {
	const { width, height } = Dimensions.get('window');
	const route = useRoute<RouteProp<RouteParams, 'params'>>();
	const router = useRouter();

	const { roomId } = route.params;
	const [rooms, setRooms] = useState<Room[]>([]);
	const [loading, setLoading] = useState(true);

	const fetchRooms2 = async () => {
		try {
			let roomsTemp = await listRooms();
			for (let i = 0; i < roomsTemp.length; i++) {
				let roomElement: Room = { id: roomsTemp[i], devices: [] };
				const devices = await listRoomDevices(roomElement.id);
				roomElement.devices = devices;
				roomsTemp[i] = roomElement;
			}

			for (let i = 0; i < roomsTemp.length; i++) {
				let tempRoom = roomsTemp[i];
				if (tempRoom.devices) {
					for (let j = 0; j < tempRoom.devices.length; j++) {
						let deviceID = tempRoom.devices[j];
						const status = await getStatusDevice(deviceID);
						roomsTemp[i].devices![j] = {id: deviceID, status: mapStatusString(status)};
					}
				}
			}
			setRooms(roomsTemp);
			setLoading(false);
		} catch (error) {
			alert('Error fetching groups: ' + error);
			setLoading(false);
		}
	};

	const turnOnAllDevicesInRoom = async (roomId: string) => {
		const result = await turnOnRoom(roomId);
		fetchRooms2();
		console.log(result);
	}

	const turnOffAllDevicesInRoom = async (roomId: string) => {
		const result = await turnOffRoom(roomId);
		fetchRooms2();
		console.log(result);
	}

	useEffect(() => {
		const fetchRooms= async () => {
		try {
			let roomsTemp = await listRooms();
			for (let i = 0; i < roomsTemp.length; i++) {
				let roomElement: Room = { id: roomsTemp[i], devices: [] };
				const devices = await listRoomDevices(roomElement.id);
				roomElement.devices = devices;
				roomsTemp[i] = roomElement;
			}

			for (let i = 0; i < roomsTemp.length; i++) {
				let tempRoom = roomsTemp[i];
				if (tempRoom.devices) {
					for (let j = 0; j < tempRoom.devices.length; j++) {
						let deviceID = tempRoom.devices[j];
						const status = await getStatusDevice(deviceID);
						roomsTemp[i].devices![j] = {id: deviceID, status: mapStatusString(status)};
					}
				}
			}
			setRooms(roomsTemp);
			setLoading(false);
		} catch (error) {
			alert('Error fetching groups: ' + error);
			setLoading(false);
		}
		};
		fetchRooms();
		}, []);

	const room = rooms.find((room) => room.id == roomId);

	if (loading) {
		return (
			<View style={{backgroundColor: blueColor, flex: 1, alignItems: 'center', justifyContent: 'center'}}>
				<Text style={{ color: '#fff', fontSize: 18 }}>Loading...</Text>
				<ActivityIndicator size="large" color="#fff" style={{ marginTop: 20 }} />
			</View>
		);
	}
	if (!room) {
		return (
			<View style={{backgroundColor: blueColor, flex: 1, alignItems: 'center', justifyContent: 'center'}}>
				<Text style={{color: 'white', fontSize: 32}}>404 Group Not Found</Text>
				<Pressable onPress={() => router.replace({pathname: '/GroupsScreens'})} style={{ marginRight: 1}}>
					<Text style={{ color: '#fff', fontSize: 24, fontWeight: 'bold', alignItems: 'center'}}>Go Back</Text>
				</Pressable>
			</View>
		);
	  }
	return (
	<View style={{backgroundColor: blueColor, flex: 1}}>
		<View style={[styles.topTextContainer, { marginTop: height * 0.075, alignItems: 'center'}]}>
			<Pressable onPress={() => router.replace({pathname: '/RoomsScreen'})} style={{ marginRight: 1}}>
			<ExpoLinearGradient colors={['#34C8E8', '#4E4AF2']} start={{ x: 0, y: 0 }} end={{ x: 1, y: 1 }} style={styles.button}>
				<Ionicons name="chevron-back" size={23} color="#fff" style={{ marginRight: 1 }} />
			</ExpoLinearGradient>
			</Pressable>
			<Text style={{ color: '#fff', fontSize: 24, fontWeight: 'bold', alignItems: 'center'}}>{roomId}</Text>
			<Pressable onPress={() => {}} style={{ marginRight: 1}}>
			{/* <ExpoLinearGradient colors={['#34C8E8', '#4E4AF2']} start={{ x: 0, y: 0 }} end={{ x: 1, y: 1 }} style={styles.button}>
				<MaterialCommunityIcons name="lead-pencil" size={23} color="#fff" style={{ marginRight: 1 }}/>
			</ExpoLinearGradient> */}
			</Pressable>
		</View>
			<ScrollView style={{flex: 1, marginTop: height * 0.05, width: width}} showsVerticalScrollIndicator={false}>
				{room.devices && room.devices.map((device) => (
					(device === undefined) ? (
						<View style={[{height: height * 0.13, paddingVertical: height * 0.08}, styles.deviceslistitem]}>
						</View>
					) : (
					<View key={device.id} style={[{height: height * 0.13, width: width, paddingVertical: height * 0.08}, styles.deviceslistitem]}>
						<ExpoLinearGradient colors={['#363E51', '#4C5770']} start={{x:0, y:0}} end={{x:1, y:1}} style={[{width: width * 0.2, height: width * 0.2}, styles.deviceImage]}>
							<Image source={getDeviceImage(device.id)} style={{ width:  width * 0.13, height:  width * 0.13}} />
						</ExpoLinearGradient>
						<View style={{flexDirection: 'column', height: height * 0.13, width: '60%', justifyContent: 'center', marginLeft: 15}}>
							<Text style={{color: '#fff', fontSize: 16,}}>{device.id}</Text>
							<View style={{flexDirection: 'row', alignItems: 'center', marginTop: 4}}>
								<View style={{marginTop: 4, width: 12, height: 12, borderRadius: 6, opacity: 0.87, backgroundColor: device.status === 'On' ? '#00FF2F' : '#FF0000', marginRight: 5}}></View>
								<Text style={{fontSize: 16, fontWeight: 'bold', opacity: 0.87, color: device.status === 'On' ? '#00FF2F' : '#FF0000' }}>{device.status}</Text>
							</View>
						</View>
						<Pressable onPress={() => router.push({pathname: './DeviceScreen', params: {deviceId: device.id, roomId: roomId}})} style={{ marginRight: 1}}>
							<ExpoLinearGradient colors={['#34C8E8', '#4E4AF2']} start={{ x: 0, y: 0 }} end={{ x: 1, y: 1 }} style={styles.button}>
								<Ionicons name="chevron-forward" size={23} color="#fff" style={{ marginRight: 1 }} />
							</ExpoLinearGradient>
						</Pressable>
					</View>
					)
				))}

			</ScrollView>
		<View style={{ flexDirection:'row', justifyContent: 'space-around', marginBottom: height * 0.035, paddingVertical: height * 0.02, backgroundColor:'transparent'}}>
			<Pressable onPress={() => turnOffAllDevicesInRoom(roomId)} style={{ marginRight: 1}}>
				<ExpoLinearGradient colors={['#34C8E8', '#4E4AF2']} start={{ x: 0, y: 0 }} end={{ x: 1, y: 1 }} style={[styles.button, {width: width * 0.375}]}>
					<Text style={{ color: '#fff', fontSize: 20, fontWeight: 'bold', alignItems: 'center', paddingHorizontal: 10}}>Turn Off All</Text>
				</ExpoLinearGradient>
			</Pressable>
			<Pressable onPress={() => turnOnAllDevicesInRoom(roomId)} style={{ marginRight: 1}}>
				<ExpoLinearGradient colors={['#34C8E8', '#4E4AF2']} start={{ x: 0, y: 0 }} end={{ x: 1, y: 1 }} style={[styles.button, {width: width * 0.375}]}>
					<Text style={{ color: '#fff', fontSize: 20, fontWeight: 'bold', alignItems: 'center', paddingHorizontal: 10}}>Turn On All</Text>
				</ExpoLinearGradient>
			</Pressable>
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
    borderRadius: 16,
    justifyContent: 'center',
    alignItems: 'center',
    shadowColor: '#000',
    shadowOpacity: 0.2,
    shadowOffset: { width: 0, height: 2 },
    shadowRadius: 6,
    elevation: 3,
  },
  deviceslistitem: {
	width: '100%',
	justifyContent: 'space-around',
	alignItems: 'center',
	flexDirection: 'row',
	borderBottomWidth: 1,
	borderBottomColor: 'rgba(255, 255, 255, 0.1)',
	paddingHorizontal: 20,
  },
});

export default RoomScreen;
