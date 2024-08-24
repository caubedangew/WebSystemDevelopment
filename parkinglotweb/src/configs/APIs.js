const BASE_URL = 'http://localhost:8080/api/';

export const endpoints = {
    'parkinglot': '/parkinglot',
    'parkingspace': (parkinglotId) => `/parkingspace/${parkinglotId}`,
    'login': '/login',
    'current-user': '/current-user',
    'register': '/register'
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