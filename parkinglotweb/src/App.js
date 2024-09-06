import './App.css';
import { BrowserRouter, Route, Routes } from 'react-router-dom';
import Home from './components/Home';
import Parkinglot from './components/Parking/Parkinglot';
import Parkingspace from './components/Parking/Parkingspace';
import { useReducer } from 'react';
import MyUserReducer from './reducers/MyUserReducer';
import { MyDispatchContext, MyUserContext } from './context/MyContext';
import Header from './components/layout/Header';
import { Container } from 'react-bootstrap';
import Footer from './components/layout/Footer';
import Login from './components/Users/Login';
import Register from './components/Users/Register';
import Comment from './components/Comment';
import Profile from './components/Users/Profile';
import Chat from './components/Users/chat';


function App() {
  const [user, dispatch] = useReducer(MyUserReducer, null);
  
  return (
    <MyUserContext.Provider value={user}>
      <MyDispatchContext.Provider value={dispatch}>
        <BrowserRouter>
          <Header />
          <Container>
            <Routes>
              <Route path="/parkinglot" element={<Parkinglot />} />
              <Route path="/" element={<Home />} />
              <Route path="parkinglot/:parkinglotId/parkingspace" element={<Parkingspace />} />
              <Route path="parkinglot/:parkinglotId/comments" element={<Comment />} />
              <Route path="/login" element={<Login />} />
              <Route path="/register" element={<Register />} />
              <Route path="/profile" element={<Profile />}/>
              <Route path="/chat" element={<Chat />}/>
            </Routes>
          </Container>
          <Footer />
        </BrowserRouter>
      </MyDispatchContext.Provider>
    </MyUserContext.Provider>
  );
}

export default App;
