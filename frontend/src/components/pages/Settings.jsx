import React, { useState } from 'react'
import { Header, SideNabvar } from '../ui'
import './Settings.css'
import Swal from 'sweetalert2'
import { useAuthStore } from "../../hooks/useAuthStore";


export const Settings = () => {

    const {startLogout} = useAuthStore();

    const showAlert = () => {
        Swal.fire({
            title: '¿Quieres cerrar sesión?',
            showDenyButton: true,
            denyButtonColor: 'var(--verdeLimon)',
            showCancelButton: false,
            confirmButtonText: 'Si',
            confirmButtonColor: '#d33',
            denyButtonText: `No`,
          }).then((result) => {
            /* Read more about isConfirmed, isDenied below */
            if (result.isConfirmed) {
                startLogout();
                Swal.fire('Sesión cerrada', '', 'success')
            } else if (result.isDenied) {
                Swal.fire('Sesión no cerrada', '', 'info')
            }
        })
    }

    const showAlert2 = () => {
        Swal.fire({
            title: 'Necesitas ayuda?',
            showDenyButton: true,
            showCancelButton: false,
            confirmButtonText: 'Si',
            confirmButtonColor: 'var(--verdeLimon)',
            denyButtonText: `No`,
          }).then((result) => {
            /* Read more about isConfirmed, isDenied below */
            if (result.isConfirmed) {
              Swal.fire('Aqui aparecera la ayuda', '' ,'info')
            }
        })
    }

    

  return (
    <div>
      <Header />
      <SideNabvar />
      <div className="container">
        <h1>Configuración</h1>
        <br />

        <div>
            <button className='boton' onClick={()=>showAlert2()}>
                <h6> <i className='fa-solid fa-circle-info'></i> Ayuda</h6>
            </button>
        </div>

        <div>
            <button className='boton' onClick={()=>showAlert()}>
                <h6> <i className='fa-solid fa-right-from-bracket'></i> Cerrar sesión</h6>
            </button>
        </div>

      </div>
    </div>
  )
}