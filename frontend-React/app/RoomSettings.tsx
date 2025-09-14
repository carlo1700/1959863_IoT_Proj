import React, { useEffect } from 'react';
import { View, Text, StyleSheet, Dimensions, Image, Pressable, FlatList, Button, ScrollView, TextInput } from 'react-native';
import { RouteProp, useRoute } from '@react-navigation/native';
import { Ionicons } from '@expo/vector-icons';
import { LinearGradient as ExpoLinearGradient } from 'expo-linear-gradient';
import { useRouter } from 'expo-router';
import MaterialCommunityIcons from '@expo/vector-icons/MaterialCommunityIcons';

const lightAzureColor = '#34C8E8';
const azureColor = '#4E4AF2';
const blueColor = '#242C3B';

type Device = {
  id: string;
  status?: string;
};

type Room = {
  id: string;
  devices?: Device[];
};

type RouteParams = {
  params: {
    roomId: string;
	roomsTemp: string;
  };
};

export default function RoomSettings() {
  const { width, height } = Dimensions.get('window');
  const router = useRouter();
  const route = useRoute<RouteProp<RouteParams>>();
  const { roomId, roomsTemp } = route.params;
  const rooms = JSON.parse(roomsTemp) as Room[];
  const room = rooms.find((room) => room.id == roomId);
  if (!room) {
	return (
		<View style={{backgroundColor: blueColor, flex: 1, alignItems: 'center', justifyContent: 'center'}}>
			<Text style={{color: lightAzureColor, fontSize: 32}}>404 Room Not Found</Text>
			<Pressable onPress={() => router.back()} style={{ marginRight: 1}}>
				<Text style={{ color: '#fff', fontSize: 24, fontWeight: 'bold', alignItems: 'center'}}>Go Back</Text>
			</Pressable>
		</View>
	);
  }
  const [roomName, setRoomName] = React.useState(room.id);
  function saveNameFromTextInput() {
	// usa api per salvare
  }

  return (
	<View style={{backgroundColor: blueColor, flex: 1, overflow: 'hidden'}}>
		<View style={[styles.topTextContainer, { marginTop: height * 0.075, alignItems: 'center', width: width}]}>
			<Pressable onPress={() => router.back()} style={{ marginRight: 1}}>
			  <ExpoLinearGradient colors={['#34C8E8', '#4E4AF2']} start={{ x: 0, y: 0 }} end={{ x: 1, y: 1 }} style={styles.button}>
				<Ionicons name="chevron-back" size={23} color="#fff" style={{ marginRight: 1 }} />
			  </ExpoLinearGradient>
			</Pressable>
			<Text style={{ color: '#fff', fontSize: 24, fontWeight: 'bold', alignItems: 'center', marginLeft: width * 0.125, width: '100%'}}>Modify Room</Text>
		</View>
		<View style={{marginTop: height * 0.06, width: width, alignItems: 'center'}}>
			<ExpoLinearGradient colors={['#363E51', '#4C5770']} start={{x:0, y:0}} end={{x:1, y:1}} style={[styles.button2, {width: width * 0.7, marginTop: 3, borderRadius: 50}]}>
				<View style={{width: width * 0.55, paddingVertical: height * 0.015, paddingHorizontal: width * 0.007, flexDirection: 'row', alignItems: 'center'}}>
					<TextInput style={{fontSize: 26, fontWeight: 'bold', color: 'white', width: '100%'}} value={roomName} onChangeText={setRoomName} />
					<MaterialCommunityIcons name="lead-pencil" size={26} color="#fff" style={{ marginTop: 3 }}/>
				</View>
			</ExpoLinearGradient>
      <ScrollView style={{width: width * 0.9, height: height * 0.4, marginTop: height * 0.05}}>
          {room.devices?.map((device) => (
            <View key={device.id} style={{flexDirection: 'row', alignItems: 'center', justifyContent: 'space-between'}}>
              <Text style={{fontSize: 24, marginVertical: height * 0.025, color: '#fff'}}>{device.id}</Text>
            </View>
          ))}
      </ScrollView>
			<Pressable onPress={() => saveNameFromTextInput()} style={{ marginRight: 1, borderTopWidth: 2, borderColor: lightAzureColor, width: width, marginTop: height * 0.05, alignItems: 'center'}}>
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
