import React from 'react';
import ReactDOM from 'react-dom/client';
import './index.css';
import App from './App';
import reportWebVitals from './reportWebVitals';
import "bootstrap/dist/css/bootstrap.min.css";
//import 'semantic-ui-css/semantic.min.css';
import Button from '@mui/material/Button';
import { User } from './ServiceObjects/User';
import { ConnectApi } from './API/ConnectApi';
const connectApi = new ConnectApi();
const get_online_user  = async () => {
  let response = await connectApi.get_online_user();
  console.log("the online userrr");
  console.log(response.value);
  localStorage.setItem('user', response.value);
}
const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(
  <React.StrictMode>
    <App user={get_online_user.bind(this)}/>
  </React.StrictMode>
);

// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
reportWebVitals();