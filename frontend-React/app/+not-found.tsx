import { Link, Stack } from 'expo-router';
import { StyleSheet, TouchableOpacity, Image, Dimensions } from 'react-native';
import { useRouter } from 'expo-router';

import { ThemedText } from '@/components/ThemedText';
import { ThemedView } from '@/components/ThemedView';


export default function NotFoundScreen() {
  const router = useRouter();
  const { width, height } = Dimensions.get('window');

  return (
    <>
      <Stack.Screen options={{ title: 'Oops!' }} />
      <ThemedView style={styles.container}>
        <ThemedText type="title" style={{textAlign:'center'}}>This screen doesn't exist</ThemedText>
        <ThemedText type="title" style={{marginTop: 20, textAlign:'center', fontSize: 40}}>404</ThemedText>
        <TouchableOpacity onPress={() => router.back()} style={styles.link}>
          <ThemedText>Go Back</ThemedText>
        </TouchableOpacity>
      </ThemedView>
    </>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    alignItems: 'center',
    justifyContent: 'center',
    padding: 20,
    backgroundColor: '#005381',
  },
  link: {
    marginTop: 15,
    paddingVertical: 15,
  },
});
