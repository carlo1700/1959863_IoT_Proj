import React, { use, useEffect, useState } from 'react';
import { View, Text, StyleSheet, Dimensions, Image, Pressable, FlatList, Button, ScrollView, TextInput, ActivityIndicator, KeyboardAvoidingView, Platform } from 'react-native';
import { RouteProp, useRoute } from '@react-navigation/native';
import { Ionicons } from '@expo/vector-icons';
import { LinearGradient as ExpoLinearGradient } from 'expo-linear-gradient';
import { useRouter } from 'expo-router';
import MaterialCommunityIcons from '@expo/vector-icons/MaterialCommunityIcons';

import { getDevices, getStatusDevice, turnOnDevice, turnOffDevice, startDevice, stopDevice, setUpBlind, setDownBlind, setProgramDevice, setTemperatureOven }  from '../services/apis';

import { getDeviceImage, mapStatusString, mapStatusDevice, getStatusColor, mapPrograms, getNames } from '../services/utils';

const lightAzureColor = '#34C8E8';
const azureColor = '#4E4AF2';
const blueColor = '#242C3B';

type Device = {
	id: string;
	status?: string;
	name?: string;

	room?: string;
	groups?: string;
	statusOn?: string;

	// washing machine, dishwasher
	statusRunning?: string;
	remainingTime?: string;

	// oven, washing machine, dishwasher, airconditioner
	program?: string;

	// oven, thermostat
	temperature?: string;

	//oven
	targetTemperature?: string;

	// blind
	statusUp?: string;

	movementDetected?: string;

	powerOutput?: string;
	batteryStatus?: string;
}

const ACModes = ['AIR_PROGRAM_UNSPECIFIED', 'AIR_PROGRAM_COOL', 'AIR_PROGRAM_HEAT'];

const ovenModes = ['OVEN_PROGRAM_UNSPECIFIED', 'OVEN_PROGRAM_STATIC', 'OVEN_PROGRAM_CONVECTION'];
const washingModes = ['DELICATE', 'QUICK_WASH'];
const dishModes = ['NORMAL', 'ECO', 'INTENSIVE', 'QUICK'];

type RouteParams = {
  params: {
    deviceId: string;
	groupId?: string;
	roomId?: string;
  };
};

const DeviceScreen = () => {
	const { width, height } = Dimensions.get('window');
	const route = useRoute<RouteProp<RouteParams, 'params'>>();
	const router = useRouter();
	const { deviceId, groupId, roomId } = route.params;

	const [isLoading, setIsLoading] = useState(true);
	const [device, setDevice] = useState<Device | null>(null);
	const [deviceName, setDeviceName] = useState(deviceId);

	const [statusOn, setStatusOn] = useState<'On' | 'Off' | 'unknown'>('unknown');
	const [statusRunning, setStatusRunning] = useState<'Running' | 'Not Running' | 'unknown'>('unknown');
	const [statusUp, setStatusUp] = useState<'Up' | 'Down' | 'unknown'>('unknown');

	const [currentTemperature, setCurrentTemperature] = useState('0');
	const [setTemperature, setSetTemperature] = useState('0°C');

	const [targetTemperature, setTargetTemperature] = useState('0');

	const [ovenMode, setOvenMode] = useState<string | null>(null);
	const [airConditionerMode, setAirConditionerMode] = useState<string | null>(null);
	const [dishwasherMode, setDishwasherMode] = useState<string | null>(null);
	const [washingMachineMode, setWashingMachineMode] = useState<string | null>(null);

	const [remainingTime, setRemainingTime] = useState('0');
	const [movement, setMovement] = useState('Not Detected');

	const [ powerOutput, setPowerOutput ] = useState('0');
	const [ batteryStatus, setBatteryStatus ] = useState('Unknown');

	const [namesMap, setNamesMap] = useState<{[key: string]: string}>({});

	useEffect(() => {
		const fetchDevices = async () => {
			try {
				const devicesTemp = await getDevices();
				//setDevices(devicesTemp.map ((device: any) => ({ id: device.deviceId } as Device)));
				const foundDevice = devicesTemp.find((d: any) => d.deviceId === deviceId);
				if (foundDevice) {
					let deviceToInsert = { id: deviceId } as Device;
					const foundStatus = await getStatusDevice(String(deviceId));
					const statusArray = mapStatusDevice(String(deviceId), foundStatus);
					console.log('Found status: ', foundStatus);
					if (statusArray[0] === 'blind') {
						deviceToInsert.statusUp = statusArray[1];
						setStatusUp(statusArray[1] === 'Up' ? 'Up' : 'Down');
					} else if (statusArray[0] === 'oven') {
						deviceToInsert.statusOn = statusArray[1];
						deviceToInsert.temperature = statusArray[2];
						deviceToInsert.program = statusArray[3];
						deviceToInsert.targetTemperature = statusArray[4];
						setStatusOn(statusArray[1] === 'On' ? 'On' : 'Off');
						setOvenMode(mapPrograms(deviceId, statusArray[3]));
						setCurrentTemperature(statusArray[2]);
						setTargetTemperature(statusArray[4]);
					} else if (statusArray[0] === 'thermostat') {
						deviceToInsert.temperature = statusArray[1];
					} else if (statusArray[0] === 'airconditioner') {
						deviceToInsert.statusOn = statusArray[1];
						deviceToInsert.program = statusArray[2];
						setStatusOn(statusArray[1] === 'On' ? 'On' : 'Off');
						setAirConditionerMode(mapPrograms(deviceId, statusArray[2]));
					} else if (statusArray[0] === 'dishwasher') {
						deviceToInsert.statusOn = statusArray[1];
						deviceToInsert.statusRunning = statusArray[2];
						deviceToInsert.program = statusArray[3];
						deviceToInsert.remainingTime = statusArray[4];
						setStatusOn(statusArray[1] === 'On' ? 'On' : 'Off');
						setStatusRunning(statusArray[2] === 'Running' ? 'Running' : 'Not Running');
						setDishwasherMode(mapPrograms(deviceId, statusArray[3]));
						setRemainingTime(statusArray[4]);
					} else if (statusArray[0] === 'washingmachine') {
						deviceToInsert.statusOn = statusArray[1];
						deviceToInsert.statusRunning = statusArray[2];
						deviceToInsert.program = statusArray[3];
						setStatusOn(statusArray[1] === 'On' ? 'On' : 'Off');
						setStatusRunning(statusArray[2] === 'Running' ? 'Running' : 'Not Running');
						setWashingMachineMode(mapPrograms(deviceId, statusArray[3]));
						console.log('Washing machine status: ', statusArray);
					} else if (statusArray[0] === 'light') {
						deviceToInsert.statusOn = statusArray[1];
						setStatusOn(statusArray[1] === 'On' ? 'On' : 'Off');
					} else if (statusArray[0] === 'motionsensor') {
						deviceToInsert.statusOn = statusArray[1];
						setStatusOn(statusArray[1] === 'On' ? 'On' : 'Off');
						deviceToInsert.movementDetected = statusArray[2];
						setMovement(statusArray[2]);
					} else if (statusArray[0] === 'solarpanel') {
						deviceToInsert.powerOutput = statusArray[1];
						deviceToInsert.batteryStatus = statusArray[2];
						setPowerOutput(statusArray[1]);
						setBatteryStatus(statusArray[2]);
					}

					deviceToInsert.status = mapStatusString(foundStatus);
					setDevice(deviceToInsert);
					setDeviceName(String(deviceId));
				}
			} catch (error) {
				alert('Error fetching devices: ' + error);
			}
			setIsLoading(false);

			const names = await getNames();
			setNamesMap(names as {[key: string]: string});
		};
		fetchDevices();
	}, []);

	const [blockTouch, setBlockTouch] = useState(false);

	const [isOpenAC, setIsOpenAC] = useState(false);
	const [isOpenOven, setIsOpenOven] = useState(false);
	const [isOpenWashing, setIsOpenWashing] = useState(false);
	const [isOpenDish, setIsOpenDish] = useState(false);

	const toggleStatus = async (deviceType: string) => {
		if (deviceType == 'light' || deviceType == 'air-conditioner' || deviceType == 'oven'
			|| deviceType == 'sensor' || deviceType == 'dish washer' || deviceType == 'washing machine') {
				if (statusRunning === 'Running') setStatusRunning('Not Running');
				statusOn === 'On' ? setStatusOn('Off') : setStatusOn('On');
		} else if (deviceType == 'blind') {
			statusUp === 'Up' ? setStatusUp('Down') : setStatusUp('Up');
		}
	};

	const toggleStatusRunning = async (deviceType: string) => {
		if (deviceType == 'dish washer' || deviceType == 'washing machine') {
			if (statusOn === 'Off') setStatusOn('On');
			statusRunning === 'Running' ? setStatusRunning('Not Running') : setStatusRunning('Running');
		}
	};

	const getDeviceType = (deviceName: string) => {
		const name = deviceName.toLowerCase();
		if (name.includes('light')) return 'light';
		if (name.includes('airconditioner')) return 'air-conditioner';
		if (name.includes('blind')) return 'blind';
		if (name.includes('sensor')) return 'sensor';
		if (name.includes('oven')) return 'oven';
		if (name.includes('radiator')) return 'radiator';
		if (name.includes('solar')) return 'solar';
		if (name.includes('washingmachine')) return 'washing machine';
		if (name.includes('thermostat')) return 'thermostat';
		if (name.includes('dishwasher')) return 'dish washer';
		return 'unknown';
	};

	type Devices = {
		deviceType: 'light' | 'air-conditioner' | 'blind' | 'sensor' | 'oven' | 'radiator' | 'solar' | 'washing machine' | 'thermostat' | 'dish washer';
	};

	function goBack() {
		if (groupId) {
			router.replace({pathname: './GroupScreen', params: {groupId: groupId}});
		} else if (roomId) {
			router.replace({pathname: './RoomScreen', params: {roomId: roomId}});
		} else {
			router.back();
		}
	}

	const blockForAWhile = () => {
		setBlockTouch(true);
		setTimeout(() => setBlockTouch(false), 3000);
	};

	async function saveChanges() {
		try {
			switch (getDeviceType(deviceName)) {
				case 'light':
					if (statusOn === 'On') {
						await turnOnDevice(String(deviceId));
					} else {
						await turnOffDevice(String(deviceId));
					}
					break;
				case 'air-conditioner':
					if (statusOn === 'On') {
						await turnOnDevice(String(deviceId));
					} else {
						await turnOffDevice(String(deviceId));
					}
					if (airConditionerMode) {
						const programIndex = ACModes.indexOf(airConditionerMode);
						if (programIndex !== -1) {
							await setProgramDevice(String(deviceId), programIndex);
						}
					}
					break;
				case 'oven':
					if (statusOn === 'On') {
						await turnOnDevice(String(deviceId));
					} else {
						await turnOffDevice(String(deviceId));
					}
					if (ovenMode) {
						const programIndex = ovenModes.indexOf(ovenMode);
						if (programIndex !== -1) {
							await setProgramDevice(String(deviceId), programIndex);
						}
					}
					if (setTemperature) {
						await setTemperatureOven(String(deviceId), Number(setTemperature.replace('°C', '')));
					}
					break;
				case 'blind':
					if (statusUp === 'Up') {
						await setUpBlind(String(deviceId));
					} else {
						await setDownBlind(String(deviceId));
					}
					break;
				case 'dish washer':
					console.log('Saving changes for dishwasher:', { statusOn, statusRunning, dishwasherMode });
					if (statusOn == 'On') {
						console.log('Turning on device, deviceId:', deviceId);
						await turnOnDevice(String(deviceId));
					} else {
						console.log('Turning off device, deviceId:', deviceId);
						await turnOffDevice(String(deviceId));
					}
					if (statusRunning === 'Running') {
						console.log('Starting device');
						await startDevice(String(deviceId));
					} else {
						console.log('Stopping device');
						await stopDevice(String(deviceId));
					}
					if (dishwasherMode) {
						const programIndex = dishModes.indexOf(dishwasherMode);
						if (programIndex !== -1) {
							console.log('Setting program:', programIndex);
							await setProgramDevice(String(deviceId), programIndex);
						}
					}
					break;
				case 'washing machine':
					if (statusOn === 'On') {
						await turnOnDevice(String(deviceId));
					} else {
						await turnOffDevice(String(deviceId));
					}
					if (statusRunning === 'Running') {
						await startDevice(String(deviceId));
					} else {
						await stopDevice(String(deviceId));
					}
					if (washingMachineMode) {
						const programIndex = washingModes.indexOf(washingMachineMode);
						if (programIndex !== -1) {
							await setProgramDevice(String(deviceId), programIndex);
						}
					}
					break;
				case 'sensor':
					if (statusOn === 'On') {
						await turnOnDevice(String(deviceId));
					} else {
						await turnOffDevice(String(deviceId));
					}
					break;
				default:
					break;
			}
			alert('Changes saved successfully!');
			blockForAWhile();
			goBack();
		} catch (error) {
			alert('Error saving changes: ' + error);
			console.error('Error saving changes:', error);
		}
	}

  function DeviceSpecificView({ deviceType }: Devices ) {
	return (
		<View style={{flex: 1, width: width, alignItems: 'center', justifyContent: 'center', marginTop: height * 0.03}}>
			{deviceType === 'light' && (
				<View style={{flex: 1, width: width, alignItems: 'center'}}>
					<View style={{flexDirection: 'row', alignItems: 'center', marginTop: height * 0.025}}>
						<View style={{marginTop: 8, width: 22, height: 22, borderRadius: 11, opacity: 0.87, backgroundColor: getStatusColor(deviceId, statusOn), marginRight: 10}}></View>
						<Text style={{fontSize: 32, fontWeight: 'bold', textAlign: 'center', color: getStatusColor(deviceId, statusOn)}}>{statusOn}</Text>
					</View>
					<Pressable onPress={() => toggleStatus(deviceType)} style={{ marginRight: 1}}>
						<ExpoLinearGradient colors={['#34C8E8', '#4E4AF2']} start={{ x: 0, y: 0 }} end={{ x: 1, y: 1 }} style={[styles.button2, {width: width * 0.4, height: height * 0.07, marginTop: height * 0.05}]}>
							<Text style={{ color: '#fff', fontSize: 24, fontWeight: 'bold', alignItems: 'center'}}>{statusOn === 'On' ? 'Turn Off' : 'Turn On'}</Text>
						</ExpoLinearGradient>
					</Pressable>
				</View>
			)}
			{deviceType === 'air-conditioner' && (
				<View style={{flex: 1, width: width, alignItems: 'center'}}>
					<View style={{flexDirection: 'row', alignItems: 'center', marginTop: height * 0.025}}>
						<View style={{flexDirection: 'row', justifyContent: 'center', marginRight: width * 0.1}}>
							<View style={{marginTop: 14, width: 22, height: 22, borderRadius: 11, opacity: 0.87, backgroundColor: getStatusColor(deviceId, statusOn), marginRight: 10}}></View>
							<Text style={{fontSize: 32, fontWeight: 'bold', textAlign: 'center', color: getStatusColor(deviceId, statusOn)}}>{statusOn}</Text>
						</View>
						<Pressable onPress={() => toggleStatus(deviceType)} style={{ marginRight: 1}}>
							<ExpoLinearGradient colors={['#34C8E8', '#4E4AF2']} start={{ x: 0, y: 0 }} end={{ x: 1, y: 1 }} style={[styles.button2, {width: width * 0.4, height: height * 0.07}]}>
								<Text style={{ color: '#fff', fontSize: 24, fontWeight: 'bold', alignItems: 'center'}}>{statusOn === 'On' ? 'Turn Off' : 'Turn On'}</Text>
							</ExpoLinearGradient>
						</Pressable>
					</View>
					<ExpoLinearGradient colors={['#363E51', '#4C5770']} start={{x:0, y:0}} end={{x:2, y:1}} style={[{width: width * 0.6, height: height * 0.07, marginTop: height * 0.04,  zIndex:3}, styles.button2]}>
						<Pressable onPress={() => setIsOpenAC(!isOpenAC)} style={{flexDirection: 'row', alignItems: 'center', paddingHorizontal: 12, width: '100%'}}>
							<Ionicons name="chevron-down" size={22} color="#fff" style={{ marginTop: 3, paddingHorizontal: 12,}} />
							<Text style={{color: '#fff', fontSize: 16, alignItems: 'center'}}>Modes</Text>
						</Pressable>
					</ExpoLinearGradient>
					{isOpenAC && (
						<View style={{position:'absolute', top: height * 0.21, zIndex:2}}>
							<ExpoLinearGradient colors={['#363E51', '#4C5770']} start={{x:0, y:0}} end={{x:2, y:1}} style={[{width: width * 0.6, top: -height * 0.07, zIndex:1}, styles.button2]}>
							<View style={{height: height * 0.09}}></View>
							{ACModes.map((item, index) => (
								<Pressable key={index} style={{height: height * 0.06, width: '100%', alignItems:'center'}} onPress={() => { setIsOpenAC(false); setAirConditionerMode(item); }}>
									<Text style={{color: '#fff', fontSize: 16, alignItems: 'center'}}>{mapPrograms(deviceId, item)}</Text>
								</Pressable>
							))}
							</ExpoLinearGradient>
						</View>
					)}
					<View style={{flexDirection: 'row', justifyContent: 'space-between', width: width * 0.7, marginTop: height * 0.1, zIndex:1}}>
						<Text style={{fontSize: 22, fontWeight: 'bold', textAlign: 'center', color: '#35BBE7'}}>Mode</Text>
						<Text style={{fontSize: 22, fontWeight: 'bold', textAlign: 'center', color: 'white'}}>{mapPrograms(deviceId, airConditionerMode)}</Text>
					</View>
				</View>
			)}
			{deviceType === 'blind' && (
				<View style={{flex: 1, width: width, alignItems: 'center'}}>
					<View style={{flexDirection: 'row', alignItems: 'center', marginTop: height * 0.025}}>
						<View style={{flexDirection: 'row', justifyContent: 'center', marginRight: width * 0.1}}>
							<View style={{marginTop: 14, width: 22, height: 22, borderRadius: 11, opacity: 0.87, backgroundColor: getStatusColor(deviceId, statusUp), marginRight: 10}}></View>
							<Text style={{fontSize: 32, fontWeight: 'bold', textAlign: 'center', color: (getStatusColor(deviceId, statusUp))}}>{device && device.statusUp}</Text>
						</View>
						<Pressable onPress={() => toggleStatus(deviceType)} style={{ marginRight: 1}}>
							<ExpoLinearGradient colors={['#34C8E8', '#4E4AF2']} start={{ x: 0, y: 0 }} end={{ x: 1, y: 1 }} style={[styles.button2, {width: width * 0.4, height: height * 0.07}]}>
								<Text style={{ color: '#fff', fontSize: 24, fontWeight: 'bold', alignItems: 'center'}}>{statusUp === 'Up' ? 'Turn Down' : 'Turn Up'}</Text>
							</ExpoLinearGradient>
						</Pressable>
					</View>
				</View>
			)}
			{deviceType === 'sensor' && (
				<View style={{flex: 1, width: width, alignItems: 'center'}}>
					<View style={{flexDirection: 'row', alignItems: 'center', marginTop: height * 0.025}}>
						<View style={{marginTop: 8, width: 22, height: 22, borderRadius: 11, opacity: 0.87, backgroundColor: movement === 'Detected' ? '#00FF2F' : '#FF0000', marginRight: 10}}></View>
						<Text style={{fontSize: 32, fontWeight: 'bold', textAlign: 'center', color: (movement === 'Detected' ? '#00FF2F' : '#FF0000')}}>{movement}</Text>
					</View>
					<View style={{flexDirection: 'row', alignItems: 'center', marginTop: height * 0.025}}>
						<View style={{marginTop: 8, width: 22, height: 22, borderRadius: 11, opacity: 0.87, backgroundColor: statusOn === 'On' ? '#00FF2F' : '#FF0000', marginRight: 10}}></View>
						<Text style={{fontSize: 32, fontWeight: 'bold', textAlign: 'center', color: (statusOn === 'On' ? '#00FF2F' : '#FF0000')}}>{statusOn}</Text>
					</View>
					<Pressable onPress={() => toggleStatus(deviceType)} style={{ marginRight: 1}}>
						<ExpoLinearGradient colors={['#659eaaff', '#4E4AF2']} start={{ x: 0, y: 0 }} end={{ x: 1, y: 1 }} style={[styles.button2, {width: width * 0.4, height: height * 0.07, marginTop: height * 0.05}]}>
							<Text style={{ color: '#fff', fontSize: 24, fontWeight: 'bold', alignItems: 'center'}}>{statusOn === 'On' ? 'Turn Off' : 'Turn On'}</Text>
						</ExpoLinearGradient>
					</Pressable>
				</View>
			)}
			{deviceType === 'oven' && (
				<KeyboardAvoidingView
						style={{ flex: 1 }}
						behavior={Platform.OS === "ios" ? "padding" : "height"}
						>
					<View style={{flex: 1, width: width, alignItems: 'center'}}>
						<View style={{flexDirection: 'row', alignItems: 'center', marginTop: height * 0.025}}>
							<View style={{flexDirection: 'row', justifyContent: 'center', marginRight: width * 0.1}}>
								<View style={{marginTop: 14, width: 22, height: 22, borderRadius: 11, opacity: 0.87, backgroundColor: getStatusColor(deviceId, statusOn), marginRight: 10}}></View>
								<Text style={{fontSize: 32, fontWeight: 'bold', textAlign: 'center', color: getStatusColor(deviceId, statusOn)}}>{statusOn}</Text>
							</View>
							<Pressable onPress={() => toggleStatus(deviceType)} style={{ marginRight: 1}}>
								<ExpoLinearGradient colors={['#34C8E8', '#4E4AF2']} start={{ x: 0, y: 0 }} end={{ x: 1, y: 1 }} style={[styles.button2, {width: width * 0.4, height: height * 0.07}]}>
									<Text style={{ color: '#fff', fontSize: 24, fontWeight: 'bold', alignItems: 'center'}}>{statusOn === 'On' ? 'Turn Off' : 'Turn On'}</Text>
								</ExpoLinearGradient>
							</Pressable>
						</View>
						<ExpoLinearGradient colors={['#363E51', '#4C5770']} start={{x:0, y:0}} end={{x:2, y:1}} style={[{width: width * 0.6, height: height * 0.07, marginTop: height * 0.04,  zIndex:3}, styles.button2]}>
							<Pressable onPress={() => setIsOpenOven(!isOpenOven)} style={{flexDirection: 'row', alignItems: 'center', paddingHorizontal: 12, width: '100%'}}>
								<Ionicons name="chevron-down" size={22} color="#fff" style={{ marginTop: 3, paddingHorizontal: 12,}} />
								<Text style={{color: '#fff', fontSize: 16, alignItems: 'center'}}>Modes</Text>
							</Pressable>
						</ExpoLinearGradient>
						{isOpenOven && (
							<View style={{position:'absolute', top: height * 0.21, zIndex:2}}>
								<ExpoLinearGradient colors={['#363E51', '#4C5770']} start={{x:0, y:0}} end={{x:2, y:1}} style={[{width: width * 0.6, top: -height * 0.07, zIndex:1}, styles.button2]}>
								<View style={{height: height * 0.09}}></View>
								{ovenModes.map((item, index) => (
									<Pressable key={index} style={{height: height * 0.06, width: '100%', alignItems:'center'}} onPress={() => { setIsOpenOven(false); setOvenMode(item); }}>
										<Text style={{color: '#fff', fontSize: 16, alignItems: 'center'}}>{mapPrograms(deviceId, item)}</Text>
									</Pressable>
								))}
								</ExpoLinearGradient>
							</View>
						)}
						<View style={{flexDirection: 'row', justifyContent: 'space-between', width: width * 0.8, marginTop: height * 0.02, zIndex:1}}>
							<Text style={{fontSize: 21, fontWeight: 'bold', textAlign: 'center', color: '#35BBE7'}}>Current Mode</Text>
							<Text style={{fontSize: 21, fontWeight: 'bold', textAlign: 'center', color: 'white', width: width * 0.35}}>{mapPrograms(deviceId, ovenMode)}</Text>
						</View>
						<View style={{flexDirection: 'row', justifyContent: 'space-between', width: width * 0.8, marginTop: height * 0.0125, zIndex:1}}>
							<Text style={{fontSize: 21, fontWeight: 'bold', textAlign: 'center', color: '#35BBE7'}}>Set: </Text>
							<ExpoLinearGradient colors={['#363E51', '#4C5770']} start={{x:0, y:0}} end={{x:2, y:1}} style={[{width: width * 0.35, marginLeft: width * 0.1, marginTop: 3}, styles.button2]}>
								<TextInput style={{fontSize: 21, fontWeight: 'bold', textAlign: 'center', color: 'white', outlineStyle: 'none'}} value={setTemperature} onChangeText={(text) => setSetTemperature(text)} />
							</ExpoLinearGradient>
						</View>
						<View style={{flexDirection: 'row', justifyContent: 'space-between', width: width * 0.8, marginTop: height * 0.0125, zIndex:1}}>
							<Text style={{fontSize: 21, fontWeight: 'bold', textAlign: 'center', color: '#35BBE7'}}>Target: </Text>
							<Text style={{fontSize: 21, fontWeight: 'bold', textAlign: 'center', color: 'white', width: width * 0.35}}>{targetTemperature + '°C'}</Text>
						</View>
						<View style={{flexDirection: 'row', justifyContent: 'space-between', width: width * 0.8, marginTop: height * 0.0125, zIndex:1}}>
							<Text style={{fontSize: 21, fontWeight: 'bold', textAlign: 'center', color: '#35BBE7'}}>Current: </Text>
							<Text style={{fontSize: 21, fontWeight: 'bold', textAlign: 'center', color: 'white', width: width * 0.35}}>{currentTemperature + '°C'}</Text>
						</View>
					</View>
				</KeyboardAvoidingView>
			)}
			{deviceType === 'solar' && (
				<View>
					<View style={{flexDirection: 'row', alignItems: 'center', justifyContent: 'space-between', paddingHorizontal: width * 0.125,  width: '100%'}}>
						<Text style={{ color: lightAzureColor, fontSize: 24, fontWeight: 'bold', alignItems: 'center'}}>Battery: </Text>
						<ExpoLinearGradient colors={['#363E51', '#4C5770']} start={{x:0, y:0}} end={{x:2, y:1}} style={[{width: width * 0.31, height: height * 0.06, marginLeft: width * 0.1, marginTop: 3}, styles.button2]}>
							<Text style={{fontSize: 22, fontWeight: 'bold', textAlign: 'center', color: 'white'}}>{batteryStatus + '%'}</Text>
						</ExpoLinearGradient>
					</View>
					<View style={{flexDirection: 'row', alignItems: 'center', justifyContent: 'space-between', paddingHorizontal: width * 0.125, marginTop: height * 0.025, width: '100%'}}>
						<Text style={{ color: lightAzureColor, fontSize: 24, fontWeight: 'bold', alignItems: 'center'}}>Current Power: </Text>
						<ExpoLinearGradient colors={['#363E51', '#4C5770']} start={{x:0, y:0}} end={{x:2, y:1}} style={[{width: width * 0.31, height: height * 0.06, marginLeft: width * 0.1, marginTop: 3}, styles.button2]}>
							<Text style={{fontSize: 22, fontWeight: 'bold', textAlign: 'center', color: 'white'}}>{powerOutput + 'W'}</Text>
						</ExpoLinearGradient>
					</View>
				</View>

			)}
			{deviceType === 'washing machine' && (
				<View style={{flex: 1, width: width, alignItems: 'center'}}>
					<View style={{flexDirection: 'row', width: width, paddingHorizontal: width * 0.05, alignItems: 'center', justifyContent: 'space-between', marginTop: height * 0.02}}>
						<View style={{flexDirection: 'row', justifyContent: 'center', marginRight: width * 0.1}}>
							<View style={{marginTop: 12, width: 16, height: 16, borderRadius: 11, opacity: 0.87, backgroundColor: getStatusColor(deviceId, statusOn), marginRight: 8}}></View>
							<Text style={{fontSize: 24, fontWeight: 'bold', textAlign: 'center', color: getStatusColor(deviceId, statusOn)}}>{statusOn}</Text>
						</View>
						<Pressable onPress={() => toggleStatus(deviceType)} style={{ marginRight: 1}}>
							<ExpoLinearGradient colors={['#34C8E8', '#4E4AF2']} start={{ x: 0, y: 0 }} end={{ x: 1, y: 1 }} style={[styles.button2, {width: width * 0.35, height: height * 0.05}]}>
								<Text style={{ color: '#fff', fontSize: 21, fontWeight: 'bold', alignItems: 'center'}}>{statusOn === 'On' ? 'Turn Off' : 'Turn On'}</Text>
							</ExpoLinearGradient>
						</Pressable>
					</View>
					<View style={{flexDirection: 'row', width: width, paddingHorizontal: width * 0.05, justifyContent: 'space-between', alignItems: 'center', marginTop: height * 0.02}}>
						<View style={{flexDirection: 'row', justifyContent: 'center', marginRight: width * 0.1}}>
							<View style={{marginTop: 12, width: 16, height: 16, borderRadius: 11, opacity: 0.87, backgroundColor: statusRunning === 'Running' ? '#00FF2F' : '#FF0000', marginRight: 8}}></View>
							<Text style={{fontSize: 24, fontWeight: 'bold', textAlign: 'center', color: (statusRunning === 'Running' ? '#00FF2F' : '#FF0000')}}>{statusRunning}</Text>
						</View>
						<Pressable onPress={() => toggleStatusRunning(deviceType)} style={{ marginRight: 1}}>
							<ExpoLinearGradient colors={['#34C8E8', '#4E4AF2']} start={{ x: 0, y: 0 }} end={{ x: 1, y: 1 }} style={[styles.button2, {width: width * 0.35, height: height * 0.05}]}>
								<Text style={{ color: '#fff', fontSize: 21, fontWeight: 'bold', alignItems: 'center'}}>{statusRunning === 'Running' ? 'Stop' : 'Start'}</Text>
							</ExpoLinearGradient>
						</Pressable>
					</View>
					<ExpoLinearGradient colors={['#363E51', '#4C5770']} start={{x:0, y:0}} end={{x:2, y:1}} style={[{width: width * 0.6, height: height * 0.07, marginTop: height * 0.02,  zIndex:3}, styles.button2]}>
						<Pressable onPress={() => setIsOpenWashing(!isOpenWashing)} style={{flexDirection: 'row', alignItems: 'center', paddingHorizontal: 12, width: '100%'}}>
							<Ionicons name="chevron-down" size={22} color="#fff" style={{ marginTop: 3, paddingHorizontal: 12,}} />
							<Text style={{color: '#fff', fontSize: 16, alignItems: 'center'}}>Programs</Text>
						</Pressable>
					</ExpoLinearGradient>
					{isOpenWashing && (
						<View style={{position:'absolute', top: height * 0.247, zIndex:2}}>
							<ExpoLinearGradient colors={['#363E51', '#4C5770']} start={{x:0, y:0}} end={{x:2, y:1}} style={[{width: width * 0.6, top: -height * 0.085, zIndex:1}, styles.button2]}>
							<View style={{height: height * 0.09}}></View>
							{washingModes.map((item, index) => (
								<Pressable key={index} style={{height: height * 0.06, width: '100%', alignItems:'center'}} onPress={() => { setIsOpenWashing(false); setWashingMachineMode(item); }}>
									<Text style={{color: '#fff', fontSize: 16, alignItems: 'center'}}>{mapPrograms(deviceId, item)}</Text>
								</Pressable>
							))}
							</ExpoLinearGradient>
						</View>
					)}
					<View style={{flexDirection: 'column', alignItems: 'center', width: width * 0.5, marginTop: height * 0.02, zIndex:1}}>
						<Text style={{fontSize: 28, fontWeight: 'bold', textAlign: 'center', color: '#35BBE7', width: width * 0.7}}>Current Program:</Text>
						<ExpoLinearGradient colors={['#363E51', '#4C5770']} start={{x:0, y:0}} end={{x:2, y:1}} style={[{width: width * 0.56, height: height * 0.07, marginTop: height * 0.02,  zIndex:3}, styles.button2]}>
							<Text style={{fontSize: 26, fontWeight: 'bold', textAlign: 'center', color: 'white'}}>{mapPrograms(deviceId, washingMachineMode)}</Text>
						</ExpoLinearGradient>
					</View>
				</View>
			)}
			{deviceType === 'thermostat' && (
				<View style={{flex: 1, paddingTop: height * 0.1, width: width, alignItems: 'center'}}>
					<View style={{flexDirection: 'column', justifyContent: 'space-between', width: width * 0.9, marginTop: height * 0.05, zIndex:1}}>
						<Text style={{fontSize: 26, fontWeight: 'bold', textAlign: 'center', color: '#35BBE7'}}>Current Temperature: </Text>
						<Text style={{fontSize: 26, fontWeight: 'bold', textAlign: 'center', color: 'white', marginTop: height * 0.02}}>{device?.temperature} °C</Text>
					</View>
				</View>
			)}
			{deviceType === 'dish washer' && (
				<View style={{flex: 1, width: width, alignItems: 'center'}}>
					<View style={{flexDirection: 'row', alignItems: 'center', justifyContent: 'space-between', width: '90%'}}>
						<View style={{flexDirection: 'row', justifyContent: 'center', marginRight: width * 0.1}}>
							<View style={{marginTop: 8, width: 18, height: 18, borderRadius: 11, opacity: 0.87, backgroundColor: getStatusColor(deviceId, statusOn), marginRight: 10}}></View>
							<Text style={{fontSize: 22, fontWeight: 'bold', textAlign: 'center', color: getStatusColor(deviceId, statusOn)}}>{statusOn}</Text>
						</View>
						<Pressable onPress={() => toggleStatus(deviceType)} style={{ marginRight: 1}}>
							<ExpoLinearGradient colors={['#34C8E8', '#4E4AF2']} start={{ x: 0, y: 0 }} end={{ x: 1, y: 1 }} style={[styles.button2, {width: width * 0.35, height: height * 0.06}]}>
								<Text style={{ color: '#fff', fontSize: 21, fontWeight: 'bold', alignItems: 'center'}}>{statusOn === 'On' ? 'Turn Off' : 'Turn On'}</Text>
							</ExpoLinearGradient>
						</Pressable>
					</View>
					<View style={{flexDirection: 'row', alignItems: 'center', justifyContent: 'space-between', marginTop: height * 0.02, width: '90%'}}>
						<View style={{flexDirection: 'row', justifyContent: 'center', marginRight: width * 0.1}}>
							<View style={{marginTop: 8, width: 18, height: 18, borderRadius: 11, opacity: 0.87, backgroundColor: statusRunning === 'Running' ? '#00FF2F' : '#FF0000', marginRight: 10}}></View>
							<Text style={{fontSize: 22, fontWeight: 'bold', textAlign: 'center', color: (statusRunning === 'Running' ? '#00FF2F' : '#FF0000')}}>{statusRunning}</Text>
						</View>
						<Pressable onPress={() => toggleStatusRunning(deviceType)} style={{ marginRight: 1}}>
							<ExpoLinearGradient colors={['#34C8E8', '#4E4AF2']} start={{ x: 0, y: 0 }} end={{ x: 1, y: 1 }} style={[styles.button2, {width: width * 0.35, height: height * 0.06}]}>
								<Text style={{ color: '#fff', fontSize: 21, fontWeight: 'bold', alignItems: 'center'}}>{statusRunning === 'Running' ? 'Stop' : 'Start'}</Text>
							</ExpoLinearGradient>
						</Pressable>
					</View>
					<ExpoLinearGradient colors={['#363E51', '#4C5770']} start={{x:0, y:0}} end={{x:2, y:1}} style={[{width: width * 0.6, height: height * 0.07, marginTop: height * 0.02,  zIndex:9999}, styles.button2]}>
						<Pressable onPress={() => setIsOpenDish(!isOpenDish)} style={{flexDirection: 'row', alignItems: 'center', paddingHorizontal: 12, width: '100%'}}>
							<Ionicons name="chevron-down" size={22} color="#fff" style={{ marginTop: 3, paddingHorizontal: 12,}} />
							<Text style={{color: '#fff', fontSize: 16, alignItems: 'center'}}>Programs</Text>
						</Pressable>
					</ExpoLinearGradient>
					{isOpenDish && (
						<View style={{position:'absolute', top: height * 0.225, zIndex:999}}>
							<ExpoLinearGradient colors={['#363E51', '#4C5770']} start={{x:0, y:0}} end={{x:2, y:1}} style={[{width: width * 0.6, top: -height * 0.07, zIndex:99}, styles.button2]}>
							<View style={{height: height * 0.09}}></View>
							{dishModes.map((item, index) => (
								<Pressable key={index} style={{height: height * 0.06, width: '100%', alignItems:'center'}} onPress={() => { setIsOpenDish(false); setDishwasherMode(item); }}>
									<Text style={{color: '#fff', fontSize: 16, alignItems: 'center'}}>{mapPrograms(deviceId, item)}</Text>
								</Pressable>
							))}
							</ExpoLinearGradient>
						</View>
					)}
					<View style={{flexDirection: 'column', alignItems: 'center', width: width * 0.5, marginTop: height * 0.06, zIndex:1}}>
						<Text style={{fontSize: 28, fontWeight: 'bold', textAlign: 'center', color: '#35BBE7', width: width * 0.7}}>Current Program:</Text>
						<ExpoLinearGradient colors={['#363E51', '#4C5770']} start={{x:0, y:0}} end={{x:2, y:1}} style={[{width: width * 0.56, height: height * 0.07, marginTop: height * 0.02,  zIndex:3}, styles.button2]}>
							<Text style={{fontSize: 26, fontWeight: 'bold', textAlign: 'center', color: 'white'}}>{mapPrograms(deviceId, dishwasherMode)}</Text>
						</ExpoLinearGradient>
					</View>
				</View>

			)}
			</View>
		)
	}

	return (
		(isLoading) ? (
			<View style={{backgroundColor: blueColor, flex: 1, alignItems: 'center', justifyContent: 'center'}}>
				<Text style={{ color: '#fff', fontSize: 18 }}>Loading...</Text>
				<ActivityIndicator size="large" color="#fff" style={{ marginTop: 20 }} />
			</View>
		) : (
			(device === null) ? (
				<View style={{backgroundColor: blueColor, flex: 1, alignItems: 'center', justifyContent: 'center'}}>
					<Text style={{color: lightAzureColor, fontSize: 32}}>404 Device Not Found</Text>
					<Pressable onPress={() => goBack()} style={{ marginRight: 1}}>
						<Text style={{ color: '#fff', fontSize: 24, fontWeight: 'bold', alignItems: 'center'}}>Go Back</Text>
					</Pressable>
				</View>
			) : (
					<View style={{backgroundColor: blueColor, flex: 1, overflow: 'hidden'}}>
						{blockTouch && (<View style={styles.overlay} pointerEvents="auto" />)}
						<View style={[styles.topTextContainer, { marginTop: height * 0.075, alignItems: 'center', width: width}]}>
							<Pressable onPress={() => goBack()} style={{ zIndex: 2, marginLeft: 32}}>
								<ExpoLinearGradient colors={['#34C8E8', '#4E4AF2']} start={{ x: 0, y: 0 }} end={{ x: 1, y: 1 }} style={styles.button}>
									<Ionicons name="chevron-back" size={23} color="#fff" style={{ marginRight: 1 }} />
								</ExpoLinearGradient>
							</Pressable>
							<Pressable onPress={() => {}} style={{ zIndex:2, position: 'absolute', right: width * 0.1}}>
								{/* <ExpoLinearGradient colors={['#34C8E8', '#4E4AF2']} start={{ x: 0, y: 0 }} end={{ x: 1, y: 1 }} style={styles.button}>
									<MaterialCommunityIcons name="lead-pencil" size={23} color="#fff" style={{ marginRight: 1 }}/>
								</ExpoLinearGradient> */}
							</Pressable>
							<Text style={{ position: 'absolute', textAlign: 'center', color: '#fff', fontSize: 25, fontWeight: 'bold', alignItems: 'center', width: '100%' }}>{namesMap[deviceName] ?? deviceName}</Text>
						</View>
						<View style={{flex: 1, zIndex: 2, marginTop: height * 0.05, width: width, alignItems: 'center'}}>
							<View style={{ flexDirection: 'row', alignItems: 'center', width: width * 0.9, justifyContent: 'center'}}>
								<ExpoLinearGradient colors={['#363E51', '#4C5770']} start={{x:0, y:0}} end={{x:1, y:1}} style={[{width: width * 0.31, height: width * 0.31}, styles.deviceImage]}>
									<Image source={getDeviceImage(deviceId)} style={{ width: width * 0.22, height: width * 0.22, opacity: 0.9 }} resizeMode="contain"/>
								</ExpoLinearGradient>
							</View>
							<DeviceSpecificView deviceType={getDeviceType(deviceName) as Devices['deviceType']} />
						</View>
						{(getDeviceType(deviceName) != 'thermostat' && getDeviceType(deviceName) != 'sensor' && getDeviceType(deviceName) != 'solar') && (
							<Pressable onPress={() => saveChanges()} style={{width: width, marginBottom: height * 0.08, alignItems: 'center'}}>
								<ExpoLinearGradient colors={['#34C8E8', '#4E4AF2']} start={{ x: 0, y: 0 }} end={{ x: 1, y: 1 }} style={[styles.button2, {zIndex: 1, width: width * 0.3, height: height * 0.07}]}>
									<Text style={{ color: '#fff', fontSize: 24, fontWeight: 'bold', alignItems: 'center'}}>Save</Text>
								</ExpoLinearGradient>
							</Pressable>
						)}
					</View>
			)
		)
	);
};

const styles = StyleSheet.create({
  topTextContainer: {
    flexDirection: 'row',
    justifyContent: 'space-between',
    alignItems: 'center',
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
  deviceslistitem: {
	width: '100%',
	justifyContent: 'space-around',
	alignItems: 'center',
	flexDirection: 'row',
	borderBottomWidth: 1,
	borderBottomColor: 'rgba(255, 255, 255, 0.1)',
	paddingHorizontal: 20,
  },
  	overlay: {
		position: "absolute",
		top: 0,
		left: 0,
		width: Dimensions.get("window").width,
		height: Dimensions.get("window").height,
		backgroundColor: "transparent",
	},
});

export default DeviceScreen;
