/**
 * Sample React Native App
 * https://github.com/facebook/react-native
 *
 * @format
 * @flow
 */

import React, {Component} from 'react';
import {Image, StyleSheet, Text, View, NativeModules } from 'react-native';

export default class App extends Component {

  /**
   * 测试
   * @memberof App
   */
  testImage = () => {
    const myImage = require('./Images/icon_splash.jpg');
    const resolveAssetSource = require('react-native/Libraries/Image/resolveAssetSource');
    const resolvedImage = resolveAssetSource(myImage);
    console.log(resolvedImage);
    NativeModules.LoadImageSourceModule.showRNImage(resolvedImage);
  }

  render() {
    return (
      <View style={styles.container}>
        <Text style={styles.text} onPress={this.testImage}>Test</Text>
        <Image source={require('./Images/icon_splash.jpg')} style={styles.icon} />
      </View>
    );
  }
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    justifyContent: 'center',
    alignItems: 'center',
    backgroundColor: '#F5FCFF',
  },
  icon: {
    width: 150,
    height: 150,
  },

  text: {
    marginBottom: 20,
    fontSize: 20,
  }
});
