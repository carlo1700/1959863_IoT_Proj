import React, {useState, useEffect} from 'react';
import { View, Text, StyleSheet, Dimensions, Image, Pressable, FlatList, Button, ScrollView, ActivityIndicator } from 'react-native';
import { RouteProp, useRoute } from '@react-navigation/native';
import { Ionicons } from '@expo/vector-icons';
import { LinearGradient as ExpoLinearGradient } from 'expo-linear-gradient';
import { useRouter } from 'expo-router';
import MaterialCommunityIcons from '@expo/vector-icons/MaterialCommunityIcons';

import { listGroups, listGroupDevices, getStatusDevice, turnOffGroup, turnOnGroup } from '../services/apis';

import { getNames, getStatusColor } from '../services/utils';

const lightAzureColor = '#34C8E8';
const azureColor = '#4E4AF2';
const blueColor = '#242C3B';

type Device = {
	id: string;
	status?: string;
}

type Group = {
  id: string;
  devices?: Device[];
}


import { mapStatusString, getDeviceImage } from '../services/utils';

type RouteParams = {
  params: {
    groupId: string;
  };
};


const GroupScreen = () => {
  const { width, height } = Dimensions.get('window');
  const route = useRoute<RouteProp<RouteParams, 'params'>>();
  const router = useRouter();

  const { groupId } = route.params;
  const [groups, setGroups] = useState<Group[]>([]);
  const [loading, setLoading] = useState(true);

  const [namesMap, setNamesMap] = useState<{[key: string]: string}>({});

  const fetchGroups2 = async () => {
	  try {
		let groupsTemp = await listGroups();
		for (let i = 0; i < groupsTemp.length; i++) {
			let groupElement: Group = { id: groupsTemp[i], devices: [] };
			const devices = await listGroupDevices(groupElement.id);
			groupElement.devices = devices;
			groupsTemp[i] = groupElement;
		}

		for (let i = 0; i < groupsTemp.length; i++) {
			let tempGroup = groupsTemp[i];
			if (tempGroup.devices) {
				for (let j = 0; j < tempGroup.devices.length; j++) {
					let deviceID = tempGroup.devices[j];
					const status = await getStatusDevice(deviceID);
					groupsTemp[i].devices![j] = {id: deviceID, status: mapStatusString(status)};
				}
			}
		}
		setGroups(groupsTemp);
		setLoading(false);

		const names = await getNames();
		setNamesMap(names as {[key: string]: string});
	  } catch (error) {
		alert('Error fetching groups: ' + error);
		setLoading(false);
	  }
	};

	const turnOnAllDevicesInGroup = async (groupId: string) => {
		const result = await turnOnGroup(groupId);
		fetchGroups2();
		console.log(result);
	}

	const turnOffAllDevicesInGroup = async (groupId: string) => {
		const result = await turnOffGroup(groupId);
		fetchGroups2();
		console.log(result);
	}

	useEffect(() => {
		const fetchGroups = async () => {
			try {
				let groupsTemp = await listGroups();
				for (let i = 0; i < groupsTemp.length; i++) {
					let groupElement: Group = { id: groupsTemp[i], devices: [] };
					const devices = await listGroupDevices(groupElement.id);
					groupElement.devices = devices;
					groupsTemp[i] = groupElement;
				}

				for (let i = 0; i < groupsTemp.length; i++) {
					let tempGroup = groupsTemp[i];
					if (tempGroup.devices) {
						for (let j = 0; j < tempGroup.devices.length; j++) {
							let deviceID = tempGroup.devices[j];
							const status = await getStatusDevice(deviceID);
							groupsTemp[i].devices![j] = {id: deviceID, status: mapStatusString(status)};
						}
					}
				}
				setGroups(groupsTemp);
				setLoading(false);

				const names = await getNames();
				setNamesMap(names as {[key: string]: string});
			} catch (error) {
				alert('Error fetching groups: ' + error);
				setLoading(false);
			}
		};
		fetchGroups();
	}, []);

  const group = groups.find((group) => group.id == groupId);

  if (loading) {
	return (
		<View style={{backgroundColor: blueColor, flex: 1, alignItems: 'center', justifyContent: 'center'}}>
			<Text style={{ color: '#fff', fontSize: 18 }}>Loading...</Text>
			<ActivityIndicator size="large" color="#fff" style={{ marginTop: 20 }} />
		</View>
	);
  }
  if (!group) {
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
        <Pressable onPress={() => router.replace({pathname: '/GroupsScreens'})} style={{ marginRight: 1}}>
          <ExpoLinearGradient colors={['#34C8E8', '#4E4AF2']} start={{ x: 0, y: 0 }} end={{ x: 1, y: 1 }} style={styles.button}>
            <Ionicons name="chevron-back" size={23} color="#fff" style={{ marginRight: 1 }} />
          </ExpoLinearGradient>
        </Pressable>
        <Text style={{ color: '#fff', fontSize: 24, fontWeight: 'bold', alignItems: 'center'}}>{group.id}</Text>
		<Pressable onPress={() => router.push({pathname: './GroupSettings', params: {groupId: group.id, groupsTemp: JSON.stringify(groups) }})} style={{ marginRight: 1}}>
          <ExpoLinearGradient colors={['#34C8E8', '#4E4AF2']} start={{ x: 0, y: 0 }} end={{ x: 1, y: 1 }} style={styles.button}>
			<MaterialCommunityIcons name="lead-pencil" size={23} color="#fff" style={{ marginRight: 1 }}/>
          </ExpoLinearGradient>
        </Pressable>
      </View>
		<ScrollView style={{flex: 1, marginTop: height * 0.05, width: width}} showsVerticalScrollIndicator={false}>
			{group.devices && group.devices.map((device) => (
				(device === undefined) ? (
					<View style={[{height: height * 0.13, paddingVertical: height * 0.08}, styles.deviceslistitem]}>
					</View>
				) : (
					<View key={device.id} style={[{height: height * 0.13, width: width, paddingVertical: height * 0.08,}, styles.deviceslistitem]}>
						<ExpoLinearGradient colors={['#363E51', '#4C5770']} start={{x:0, y:0}} end={{x:1, y:1}} style={[{width: width * 0.2, height: width * 0.2}, styles.deviceImage]}>
							<Image source={getDeviceImage(device.id)} style={{ width:  width * 0.13, height:  width * 0.13}} />
						</ExpoLinearGradient>
						<View style={{flexDirection: 'column', height: height * 0.13, width: '60%', justifyContent: 'center', marginLeft: 15}}>
							<Text style={{color: '#fff', fontSize: 16,}}>{namesMap[device.id] ?? device.id}</Text>
							<View style={{flexDirection: 'row', alignItems: 'center', marginTop: 4}}>
								<View style={{marginTop: 4, width: 12, height: 12, borderRadius: 6, opacity: 0.87, backgroundColor: getStatusColor(device.id, device.status), marginRight: 5}}></View>
								<Text style={{fontSize: 16, fontWeight: 'bold', opacity: 0.87, color: getStatusColor(device.id, device.status) }}>{device.status}</Text>
							</View>
						</View>
						<Pressable onPress={() => router.push({pathname: './DeviceScreen', params: {deviceId: device.id, groupId: groupId}})} style={{ marginRight: 1}}>
							<ExpoLinearGradient colors={['#34C8E8', '#4E4AF2']} start={{ x: 0, y: 0 }} end={{ x: 1, y: 1 }} style={styles.button}>
								<Ionicons name="chevron-forward" size={23} color="#fff" style={{ marginRight: 1 }} />
							</ExpoLinearGradient>
						</Pressable>
					</View>
				)
			))}
		</ScrollView>
		<View style={{ flexDirection:'row', justifyContent: 'space-around', marginBottom: height * 0.035, paddingVertical: height * 0.02, backgroundColor:'transparent'}}>
			<Pressable onPress={() => turnOffAllDevicesInGroup(groupId)} style={{ marginRight: 1}}>
				<ExpoLinearGradient colors={['#34C8E8', '#4E4AF2']} start={{ x: 0, y: 0 }} end={{ x: 1, y: 1 }} style={[styles.button, {width: width * 0.375}]}>
					<Text style={{ color: '#fff', fontSize: 20, fontWeight: 'bold', alignItems: 'center', paddingHorizontal: 10}}>Turn Off All</Text>
				</ExpoLinearGradient>
			</Pressable>
			<Pressable onPress={() => turnOnAllDevicesInGroup(groupId)} style={{ marginRight: 1}}>
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

export default GroupScreen;
