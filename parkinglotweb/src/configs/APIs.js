import axios from "axios";
import cookie from "react-cookies";


const BASE_URL = 'http://localhost:8080/api';

export const endpoints = {
    'parkinglot': '/parkinglot',
    'parkingspace': (parkinglotId) => `/parkingspace?parkinglotId=${parkinglotId}`,
    'login': '/users/login',
    'current-user': '/users/current-user',
    'register': '/users/register'
}

export const authAPIs = () => {
    return axios.create({
        baseURL: BASE_URL,
        headers: {
            'Authorization': cookie.load("access-token")
        }
    })
}

export default axios.create({
    baseURL: BASE_URL
});