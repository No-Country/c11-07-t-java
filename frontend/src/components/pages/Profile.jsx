import { useEffect, useState } from 'react';
import axios from 'axios';
import './pages.css';
import { Header, SideNabvar } from '../ui';

export const Profile = () => {


  const [profileData, setProfileData] = useState();


  useEffect(()=>{
    axios.get(`http://localhost:8080/api/users/1?id=1`)
    .then(data => setProfileData(data))
  }, [])



  return (
    <>
      <Header/>
      <SideNabvar/>
    </>
  )
}
