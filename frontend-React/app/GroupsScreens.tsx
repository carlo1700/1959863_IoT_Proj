import React, { use, useEffect, useState } from 'react';
import { View, Text, StyleSheet, Dimensions, Image, ScrollView, Pressable} from 'react-native';
import { useRouter } from 'expo-router';
import AsyncStorage from '@react-native-async-storage/async-storage';
import { Ionicons } from '@expo/vector-icons';
import Svg, { ClipPath, Path, Defs, Rect, LinearGradient, Stop, Polygon } from 'react-native-svg';
import Entypo from '@expo/vector-icons/Entypo';

import { LinearGradient as ExpoLinearGradient } from 'expo-linear-gradient';

import { listGroups, listGroupDevices } from '../services/apis';

import { ActivityIndicator } from 'react-native';
import { getNames } from '@/services/utils';

const lightAzureColor = '#34C8E8';
const azureColor = '#4E4AF2';
const blueColor = '#242C3B';

type Device = {
	id: string;
	type: string;
}

type Group = {
  id: string;
  devices?: Device[];
}

export default function GroupScreen() {
  let { width, height } = Dimensions.get('window');
  const router = useRouter();

  const cardWidth = width * 0.36;
  const cardHeight = height * 0.265;
  const radius = width * 0.07;
  const skew = radius * 0.5;

  const [groups, setGroups] = useState<Group[]>([]);
  const [loading, setLoading] = useState(true);
  const [namesMap, setNamesMap] = useState<{[key: string]: string}>({});

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

  const path = `
	M ${radius} ${skew}
	L ${cardWidth - radius} 0
	Q ${cardWidth} 0, ${cardWidth} ${radius}
	V ${cardHeight - radius - skew}
	Q ${cardWidth} ${cardHeight - skew - (skew * 0.5)}, ${cardWidth - radius} ${cardHeight - skew}
	L ${radius} ${cardHeight}
	Q 0 ${cardHeight}, 0 ${cardHeight - radius}
	V ${radius + skew}
	Q 0 ${skew + (skew * 0.5)}, ${radius} ${skew}
	Z
  `;

  return (
	(loading) ? (
		<View style={{ flex: 1, justifyContent: 'center', alignItems: 'center', backgroundColor: blueColor }}>
			<Text style={{ color: '#fff', fontSize: 18 }}>Loading...</Text>
			<ActivityIndicator size="large" color="#fff" style={{ marginTop: 20 }} />
		</View>
	) : (
	<View style={{ flex: 1, height: height, width: width, backgroundColor: blueColor }}>
	  <View style={[styles.topTextContainer, { marginTop: height * 0.075, alignItems: 'center', width:'100%', justifyContent: 'space-around', }]}>
		<Pressable onPress={() => router.replace({pathname: '/HomeScreen'})} style={{ marginRight: 1}}>
		  <ExpoLinearGradient colors={['#34C8E8', '#4E4AF2']} start={{ x: 0, y: 0 }} end={{ x: 1, y: 1 }} style={styles.button}>
			<Ionicons name="chevron-back" size={23} color="#fff" style={{ marginRight: 1 }} />
		  </ExpoLinearGradient>
		</Pressable>
		<Text style={{ color: '#fff', fontSize: 32, fontWeight: 'bold', width:'60%', alignItems: 'center', textAlign: 'center'}}>Groups</Text>
		<Pressable onPress ={() => router.push({pathname: '/AddGroup'})} style={{ marginRight: 1}}>
		  <ExpoLinearGradient colors={['#34C8E8', '#4E4AF2']} start={{ x: 0, y: 0 }} end={{ x: 1, y: 1 }} style={styles.button}>
			<Entypo name="plus" size={28} color="#fff" style={{ marginTop: 1 }} />
		  </ExpoLinearGradient>
		</Pressable>
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
	  <ScrollView style={{ flex: 1, width: width}} contentContainerStyle={{ alignItems: 'center' }}>
		<View style={{flexDirection: 'row', flexWrap: 'wrap', justifyContent: 'center', width: width * 0.9, marginTop: height * 0.05}}>
			{groups.map((group) => (
				<Pressable style={{ margin: width * 0.04, width: cardWidth, height: cardHeight}} key={group.id} onPress ={() => router.push({pathname: '/GroupScreen', params: {groupId: group.id}})}>
					<Svg width={cardWidth} height={cardHeight} viewBox={`0 0 ${cardWidth} ${cardHeight}`} style={StyleSheet.absoluteFillObject}>
						<Defs>
							<LinearGradient id="grad2" x1="0" y1="0" x2="1" y2="1">
								<Stop offset="0" stopColor="#353F54" stopOpacity="0.9" />
								<Stop offset="1" stopColor="#222834" stopOpacity="0.8" />
							</LinearGradient>
						</Defs>
						<Path d={path} fill="url(#grad2)" />
					</Svg>
					<View style={{width: cardWidth, height: cardHeight, alignItems: 'center', justifyContent: 'center', paddingHorizontal: 14}}>
						<Text style={{ color: '#fff', fontSize: 18, marginTop: 5, textAlign: 'center' }}>{namesMap[group.id] ?? group.id}</Text>
						<Text style={{ color: '#fff', fontSize: 14, opacity: 0.6, textAlign: 'center' }}>
							Devices: {group.devices ? group.devices.length : 0}
						</Text>
					</View>
				</Pressable>
			))}
		</View>
	  </ScrollView>

	</View>
	)
  );

}

const styles = StyleSheet.create({
  cardContainer: {
	borderRadius: 20,
	overflow: 'hidden',
	backgroundColor: '#1E2440',
	shadowColor: '#000',
	shadowOffset: { width: 0, height: 6 },
	shadowOpacity: 0.3,
	shadowRadius: 10,
	elevation: 8,
	opacity: 0.95,
	justifyContent: 'center',
	alignItems: 'center',
  },
  pressableContainer: {
	position: 'absolute',
	top: 0,
	left: 0,
	width: 60,
	height: 60,
	backgroundColor: '#000',
	opacity: 0.3,
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
  topTextContainer: {
	flexDirection: 'row',
	justifyContent: 'space-between',
	alignItems: 'center',
	paddingHorizontal: 33,
  },
  meteoGradient: {
	flex: 1,
	alignItems: 'center',
	justifyContent: 'center',
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
