import './App.css';
import { BrowserRouter, Route, Routes } from 'react-router-dom';
import Home from './components/Home';
import Parkinglot from './components/Parkinglot';
import Parkingspace from './components/Parkingspace';
import { useReducer } from 'react';
import MyUserReducer from './reducers/MyUserReducer';
import { MyDispatchContext, MyUserContext } from './context/MyContext';
import Header from './components/layout/Header';
import { Container } from 'react-bootstrap';
import Footer from './components/layout/Footer';

function App() {
  const [user, dispatch] = useReducer(MyUserReducer, null);
  return (
    <MyUserContext.Provider value={user}>
      <MyDispatchContext.Provider value={dispatch}>
        <BrowserRouter>
          <Header />
          <Container>
            <Routes>
              <Route path="/" element={<Home />} />
              <Route path="/parkinglot" element={<Parkinglot />} />
              <Route path="/parkingspace" element={<Parkingspace />} />
            </Routes>
          </Container>
          <Footer />
        </BrowserRouter>
      </MyDispatchContext.Provider>
    </MyUserContext.Provider>
  );
}

export default App;
