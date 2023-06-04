import { useEffect, useState } from 'react';
import axios from 'axios';
import './pages.css';
import { Header, SideNabvar } from '../ui';

export const Profile = () => {


  const [profileData, setProfileData] = useState();


  useEffect(()=>{
    axios.get(`http://localhost:8080/api/users` )
    .then(data => setProfileData(data))
  }, [])


  console.log(profileData)

  return (
    <>
      <Header/>
      <SideNabvar/>
      <div>
        <h3>Por favor actualize su perfil</h3>
      
      </div>
    </>
  )
}
