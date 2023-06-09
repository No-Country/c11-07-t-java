import { useDispatch, useSelector } from 'react-redux';
import axios from 'axios';
import {  onChecking, onLogin, onLogout, updateUser } from '../store';
import { useNavigate } from 'react-router-dom';
import { useEffect, useState } from 'react';



export const useAuthStore = () => {



    function msgAlert(icon, message) {
        Swal.fire({
          icon: icon,
          title: message,
          showConfirmButton: false,
          timer: 1500
        })
    }

    const navigate = useNavigate();
     
    const API_URL = 'https://c11-07-t-java-production.up.railway.app';

    const { status, user, errorMessage } = useSelector( state => state.auth );
    const dispatch = useDispatch();

    const startLogin = async({ username, password }) => {

        
        dispatch( onChecking() );
        try {
            const {data} = await axios.post(API_URL + '/api/auth/authenticate',{ username, password });
            
            localStorage.setItem("token", data.token); //envio el token al localStorage
            localStorage.setItem("token-init", new Date().getTime()); //envio otro token de referencia, si no sirve lo borramos mas adelante
            dispatch(onLogin({username, password: "*******", token: data.token}));
            navigate("/calendar", {
                replace: true
            });

        } catch (error) {
           console.log(error)
        }
    }

    const startRegister = async({ username, email, password }) => {
     
        dispatch( onChecking() );
        try {
            const {data} = await axios.post(API_URL + '/api/auth/register',{ username, email, password });
            dispatch( onLogin({ name: data.name, uid: data.uid }) );
            localStorage.setItem("token", data.token); //envio el token al localStorage
            localStorage.setItem("token-init", new Date().getTime()); //envio otro token de referencia, si no sirve lo borramos mas adelante
            startLogin(username, password)
            dispatch(onLogin({username, email} ));
            navigate("/calendar", {
                replace: true
            });


        } catch (error) {
           console.log(error)
        }
    }

    const findUserByUsername = async(username)=>{
        checkAuthToken();
        return await axios.get(`https://c11-07-t-java-production.up.railway.app/api/users/findbyusername?username=${username}`)
    }

    const startUpdateUser = async({ username, password = "******", id, name, lastName, profesion }) => {
     

        findUserByUsername(username)
       
        
   try {
         await axios.put(API_URL + `/api/users/${id}`,{ name, lastName, profesion  });
         dispatch(updateUser(name, lastName, profesion));
         dispatch(onLogin({username, password, email, name, lastName, profesion} ));
         navigate("/calendar", {
             replace: true
         });
         alert("salio todo bien")

     } catch (error) {
        console.log(error)
     }
 }


    const checkAuthToken = async() => {
        const token = localStorage.getItem('token');
        if ( !token ) return dispatch( onLogout() );

        try {
            const { data } = await axios.get(API_URL + '/api/auth/authenticate');
            localStorage.setItem('token', data.token );
            localStorage.setItem('token-init-date', new Date().getTime() );
            dispatch(onLogin({username: data.username, password: data.password} ));

        } catch (error) {

            dispatch( onLogout() );
        }
    }

    const startLogout = () => {
        dispatch(onLogout());
    }



    return {
        //* Propiedades
        errorMessage,
        status, 
        user, 
        API_URL,
        //* Métodos
        checkAuthToken,
        startLogin,
        startLogout,
       startRegister,
       startUpdateUser
    }

}