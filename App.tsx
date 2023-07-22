/**
 * Sample React Native App
 * https://github.com/facebook/react-native
 *
 * @format
 */

import React, {useEffect} from 'react';
import {
  Button,
  StyleSheet,
  Text,
  Touchable,
  TouchableOpacity,
  View,
} from 'react-native';
import {NativeModules, NativeEventEmitter} from 'react-native';
const {CalendarModule} = NativeModules;

const eventEmitter = new NativeEventEmitter(CalendarModule);
const createCalenderEventPromise = async () => {
  try {
    var result = await CalendarModule.createCalenderPromise();

    console.log('promise', result);
  } catch (error) {
    console.log('error', error);
  }
};

function App(): JSX.Element {
  useEffect(() => {
    eventEmitter.addListener('EventCount', eventCount => {
      console.log('eventCount', eventCount);
    });
    return () => {
      eventEmitter.removeAllListeners('EventCount');
    };
  }, []);

  return (
    <View style={styles.container}>
      <Text>app</Text>
      <Button title="Calender event" onPress={createCalenderEventPromise} />
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    alignItems: 'center',
    justifyContent: 'center',
  },
});

export default App;
