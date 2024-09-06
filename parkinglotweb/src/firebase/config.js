// Import the functions you need from the SDKs you need
import { initializeApp } from "firebase/app";
import { getAnalytics } from "firebase/analytics";
import {getDatabase, ref, push, onValue} from "firebase/database";
// TODO: Add SDKs for Firebase products that you want to use
// https://firebase.google.com/docs/web/setup#available-libraries

// Your web app's Firebase configuration
// For Firebase JS SDK v7.20.0 and later, measurementId is optional
const firebaseConfig = {
  apiKey: "AIzaSyB70_BCSvMgpO_fickJZa7lMscWDHmoaRQ",
  authDomain: "parkinglot-9ab7d.firebaseapp.com",
  databaseURL: "https://parkinglot-9ab7d-default-rtdb.asia-southeast1.firebasedatabase.app/",
  projectId: "parkinglot-9ab7d",
  storageBucket: "parkinglot-9ab7d.appspot.com",
  messagingSenderId: "716860719144",
  appId: "1:716860719144:web:aaab28a4d73decc1c8f51d",
  measurementId: "G-ZFXB9WLD81"
};

// Initialize Firebase
const app = initializeApp(firebaseConfig);
const analytics = getAnalytics(app);


const database = getDatabase(app);
export {database, ref, push, onValue}