import React, { useState, useEffect } from 'react';
import { View, StyleSheet, TextInput, Pressable , Text, TouchableOpacity, Image, Linking  } from 'react-native';
import { useRouter } from 'expo-router';
import AsyncStorage from '@react-native-async-storage/async-storage';
import Svg, { Circle, Defs, RadialGradient, Stop } from 'react-native-svg';
import { BlurView } from 'expo-blur';
import { Dimensions } from 'react-native';
import { Ionicons } from '@expo/vector-icons';
import { login } from '../services/authService';
import { useFocusEffect  } from '@react-navigation/native';

const lightAzureColor = '#34C8E8';
const azureColor = '#4E4AF2';
const blueColor = '#242C3B';

export default function LoginScreen() {

  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const router = useRouter();
  const [isChecked, setIsChecked] = useState(false);
  const [showPassword, setShowPassword] = useState(false);
  const [showError, setShowError] = useState(false);
  const [errorText, setErrorText] = useState('');

  useFocusEffect(
    React.useCallback(() => {
      // Forza update o logica per ricalcolare gli svg
    }, [])
  );
  let { width, height } = Dimensions.get('window');
  let svgWidth = width;
  let svgHeight = height;

  const handleLogin = async () => {
    try {
      await AsyncStorage.setItem('email', email);
      await AsyncStorage.setItem('password', password);
      await AsyncStorage.setItem('rememberMe', isChecked ? 'true' : 'false');
      await AsyncStorage.setItem('accToken', 'prova'); // Placeholder for access token
      await AsyncStorage.setItem('userName', 'Mario'); // Placeholder for user name
      await AsyncStorage.setItem('awayStatus', 'false');
      await AsyncStorage.setItem('alarmStatus', 'false');
      router.push('/HomeScreen');
      // const data_comp = await login(email, password);
      // const data = data_comp[0];
      // const key = data_comp[1];
      // const acc_token = data['access_token'];
      // const userName = data['userName'];
      // const userSurname = data['userSurname'];
      // const errorCode = data['errorCode'];
      // const exipres = data['.expires'];

      // isChecked ? await AsyncStorage.setItem('rememberMe', 'true') : await AsyncStorage.setItem('rememberMe', 'false');

      // if (errorCode === 100) {
      //     await AsyncStorage.setItem('userName', userName);
      //     await AsyncStorage.setItem('userSurname', userSurname);
      //     await AsyncStorage.setItem('accToken', acc_token);
      //     await AsyncStorage.setItem('key', key);

      //     await AsyncStorage.setItem('exipres', exipres);
      //     await AsyncStorage.setItem('email', email);
      //     await AsyncStorage.setItem('password', password);
      //     router.push('/HomeScreen');
      // }
      // else {
      //     setErrorText('Login fallito, verifica le credenziali');
      //     setShowError(true);
      //     console.error('Login fallito, verifica le credenziali');
      // }
    }
    catch (error) {
        setErrorText('Errore nel login');
        setShowError(true);
        console.error('Errore nel login:', error);

    }
  };

  const toggleCheckbox = () => {
    setIsChecked(!isChecked); // Inverte lo stato
  };

  return (
    <View style={styles.overlay}>
      <Svg width={svgWidth * 2.6} height={svgWidth * 2.6} style={{position: 'absolute', top: 0, left: 0}}>
        <Defs>
          <RadialGradient id="grad1" cx="50%" cy="50%" r="50%" fx="50%" fy="50%">
            <Stop offset="25%" stopColor="rgba(54, 183, 232, 0.6)" stopOpacity="0.8" />
            <Stop offset="100%" stopColor="rgba(255, 255, 255, 0.01)" stopOpacity="0" />
          </RadialGradient>
          <RadialGradient id="grad2" cx="50%" cy="50%" r="50%" fx="50%" fy="50%">
            <Stop offset="0%" stopColor="rgba(67, 115, 235, 0.7)" stopOpacity="0.8" />
            <Stop offset="100%" stopColor="rgba(255, 255, 255, 0.01)" stopOpacity="0" />
          </RadialGradient>
        </Defs>

        <Circle
          cx="50%"
          cy="50%"
          r={svgWidth * 1.3}
          fill="url(#grad1)"
          transform={`translate(${-svgWidth * 1.04}, ${-svgWidth * 1.17})`}
        />
        <Circle
          cx="50%"
          cy="50%"
          r={svgWidth * 1.3}
          fill="url(#grad2)"
          transform={`translate(${-svgWidth * 0.4}, ${svgHeight * 0.3})`}
        />
      </Svg>

      {/* content view */}

      {/* <BlurView intensity={30} style={styles.blurContainer}> */}
          <View style={styles.box}>
            <Image
              source={require('../assets/images/logo-nobg-zoom.png')}
              style={{width: svgWidth * 0.4, marginBottom: 10, height: svgWidth * 0.12}}
            />
            <Text style={styles.title}>Login</Text>
            <Text style={[styles.normalText, {marginBottom: 11}]}>Enter email and password to log in</Text>

            <Text style={{ color: 'red', marginBottom: 10, fontSize: 13 }}>{showError ? errorText : ' '}</Text>

            <TextInput style={styles.input} placeholderTextColor="#888" id="email-login" underlineColorAndroid="transparent" placeholder="Email" nativeID="email" value={email} onChangeText={setEmail} onFocus={() => setShowError(false)}/>
            <View style={styles.input}>
              <TextInput style={{flex: 1, borderWidth: 0}} id="password-login" placeholderTextColor="#888" underlineColorAndroid="transparent" placeholder="Password" nativeID="password" value={password} onChangeText={setPassword} secureTextEntry={!showPassword} onFocus={() => setShowError(false)}/>
              <TouchableOpacity onPress={() => setShowPassword(!showPassword)} style={{padding: 5, alignSelf:'center', top: 1}}>
                <Ionicons name={showPassword ? 'eye' : 'eye-off'} size={18} color="gray" />
              </TouchableOpacity>
            </View>
            <View style={styles.options}>
              <TouchableOpacity style={styles.optionContainer} onPress={toggleCheckbox}>
                <View style={[styles.checkbox, isChecked && {}]}>
                  {isChecked && <Ionicons name="checkmark" size={16} color={lightAzureColor}/>}
                </View>
                <Text style={styles.normalText}>Remeber Me</Text>
              </TouchableOpacity>
              <TouchableOpacity onPress={() => Linking.openURL('')}>
                <Text style={[styles.normalText, {color: lightAzureColor, fontWeight: '600'}]}>Forgot password?</Text>
              </TouchableOpacity>
            </View>

            <TouchableOpacity style={[{backgroundColor: lightAzureColor, width: '90%' }, styles.button]} onPress={handleLogin}>
              <Text style={{color: '#FFF', fontSize: 16}}>Login</Text>
            </TouchableOpacity>

            {/* <View style={{ flexDirection: 'row', alignItems: 'center', paddingTop: 16,}}>
              <Text style={[styles.normalText, {paddingHorizontal:10, }]}>Non hai un account?</Text>
              <TouchableOpacity onPress={() => {router.push('/RegisterScreen');}}>
                <Text style={[styles.normalText, {color: lightAzureColor, fontWeight: '600'}]}>Registrati</Text>
              </TouchableOpacity>
            </View> */}
          </View>
    </View>
  );
}

const styles = StyleSheet.create({
  overlay: {
    flex: 1,
    justifyContent: 'center',
    alignItems: 'center',
    position: 'relative',
    backgroundColor: 'white',
    overflow: 'hidden',
  },
  box: {
    width: '88%',
    padding: 20,
    backgroundColor: 'rgba(253, 253, 253, 0.95)',
    borderRadius: 10,
    alignItems: 'center',
    justifyContent: 'center',
    paddingVertical: 30,

    // 🌟 Ombra per iOS 🌟
    shadowColor: '#000',
    shadowOffset: { width: 0, height: 2 },
    shadowOpacity: 0.15,
    shadowRadius: 3,

    // 🌟 Ombra per Android 🌟
    elevation: 3,
  },
  title: {
    fontSize: 30,
    fontWeight: 'bold',
    marginTop: 7,
    marginBottom: 7,
  },
  normalText:{
    fontSize: 14,
    color: 'grey'
  },
  // inputContainer: {
  //   flexDirection: 'row',
  //   alignItems: 'center',
  //   borderWidth: 1,
  //   borderColor: '#ccc',
  //   borderRadius: 8,
  //   paddingHorizontal: 10,
  //   backgroundColor: 'white'
  // },
  input: {
    width: '100%',
    flexDirection: 'row',
    height: 50,
    borderRadius: 10,
    marginBottom: 15,
    paddingHorizontal: 15,
    backgroundColor:'white',
  },
  options: {
    flexDirection: 'row',
    justifyContent: 'space-between',
    width: '100%',
    marginBottom: 20,
    marginTop: 10,
  },
  registerText: {
    fontSize: 14,
    color: '#007BFF',
    textAlign: 'center',
    marginTop: 20,
  },
  optionContainer: {
    flexDirection: 'row',
    alignItems: 'center',
  },
  checkbox: {
    width: 18,
    height: 18,
    borderWidth: 1.5,
    borderColor: 'grey',
    marginRight: 10,
    borderRadius: 4,
    justifyContent: 'center',
    alignItems: 'center',
  },
  button: {
    marginTop: 6,
    paddingVertical: 14,
    paddingHorizontal: 24,
    borderRadius: 8,
    alignItems: 'center',

    // 🌟 Ombra per iOS 🌟
    shadowColor: '#000',
    shadowOffset: { width: 0, height: 3 },
    shadowOpacity: 0.25,
    shadowRadius: 3,

    // 🌟 Ombra per Android 🌟
    elevation: 5,
  },
});
