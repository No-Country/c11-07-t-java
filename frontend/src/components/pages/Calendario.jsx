// eslint-disable-next-line no-unused-vars
import React, { useEffect, useState } from "react";
import Calendar from "react-calendar";
import "react-calendar/dist/Calendar.css";
import "./pages.css";
import { Header, SideNabvar } from "../ui";
import { useAuthStore } from "../../hooks";
import { faHourglass1 } from "@fortawesome/free-solid-svg-icons";
import { useLocalStorage } from "./useLocalStorage";
import Swal from "sweetalert2";

const defaultEvents = [
  {
    id: 1,
    start: "2023-06-08T13:00",
    end: "2023-06-08T13:45",
    title: "Lunch",
    description: "no ha",
    allDay: false,
    free: true,
    color: "#009788",
  },
  {
    id: 2,
    start: "2023-06-09T13:00",
    end: "2023-06-09T13:45",
    title: "Lunchds",
    description: "nods hdsda",
    allDay: false,
    free: true,
    color: "#009788",
  },
];

function msgAlert(icon, message) {
  Swal.fire({
    icon: icon,
    title: message,
    showConfirmButton: false,
    timer: 1500
  })
}

export const Calendario = () => {
  const [isChecked, setIsChecked] = useState(false);
  const [inputValue, setInputValue] = useState("");
  const [editData, setEditData] = useState(null)
  const [selectedDate, setSelectedDate] = useState([]);
  const [text, setText] = useLocalStorage("eventTitle", "");
  const [events, setEvents] = useState([]);
  const [showModal, setShowModal] = useState(false);
  const [eventText, setEventText] = useLocalStorage("eventText", "");
  const [eventTitle, setEventTitle] = useLocalStorage("eventTitle", "");
  const [selectedShift, setSelectedShift] = useLocalStorage('selectedShift', '');

  const [dataGuardia, setDataGuardia] = useState({
    id: null,
    title: "",
    description: "",
    shift: "",
    allDay: false,
  })

  useEffect(() => {
    if(editData !== null){
      setDataGuardia(editData)
    }else{
      setFormData({
        id: null,
        title: "",
        description: "",
        shift: "",
        allDay: false,
      })
    }
  }, [editData]);

  const handleSubmit = (e) => {
    e.preventDefault();

    if(dataGuardia.title.trim() !== '' && dataGuardia.description.trim() !== ''){
      if(editData !== null){
        editGuardia(dataGuardia);
      }else{
        dataGuardia.id = Date.now();
        addGuardia(dataGuardia);
        setDataGuardia({
          id: null,
          title: "",
          description: "",
          shift: "",
          allDay: false,
        })
      }
    }else{
      alert("error");
    }
  }

  const [formData, setFormData] = useState(() => {
    const saveEvent = window.localStorage.getItem("guardiaData");
    if(saveEvent){
      return JSON.parse(saveEvent);
    }else{
      return [];
    }
  });

  useEffect(() => {
    window.localStorage.setItem("guardiaData", JSON.stringify(formData));
  }, []);

  //inserción de datos
  const addGuardia = (formgGuardia) => {
    setFormData([
      ...formData,
      formgGuardia
    ]);
  }

  //editar data
  const editGuardia = (formgGuardia) => {
    const newGuardia = formData.map(gu => gu.id === formgGuardia.id ? formgGuardia : gu);
    setFormData(newGuardia);
    setEditData(null);
  }

  //eliminar data
  const deleteGuardia = id => {
    const isDelete = window.confirm(`¿Eiminar?${id}`);

    if(isDelete) {
      const newGuardia = formData.filter(gu => gu.id !== id);
      setFormData(newGuardia);
    }
  }

  const handleChange = (e) => {
    console.log(e.target);
  };

  const { user } = useAuthStore();

  const handleDateChange = (date) => {
    setSelectedDate(date);
  };

  const handleAddEvent = () => {
    setShowModal(true);
  };

  const handleSaveEvent = () => {
    if (
      selectedDate && 
      eventTitle.trim() && 
      eventText.trim() !== "") {
      const newEvent = {
        date: selectedDate,
        title: eventTitle,
        description: eventText,
        
      };

      //console.log(eventShift);

      setEvents([...events, newEvent]);
      setSelectedDate([]);
      setEventText("");
      setShowModal(false);
    }else{
      msgAlert("error", "Completa el formulario")
    }
  };

  const handleOptionChange = (e) => {
    setSelectedShift(e.target.value);
  };

  const handleModalClose = () => {
    setShowModal(false);
  };

  const handleCheckboxChange = () => {
    setIsChecked(!isChecked);
  };

  const handleInputChange = (e) => {
    if (!isChecked) {
      setInputValue(e.target.value);
    }
  };

  return (
    <div className="calendario">
      <Header />
      <SideNabvar />
      <h2 className="titulo">Hola {user.username}!</h2>
      <h2 className="titulo">Mis Guardias</h2>
      <div className="container">
        <Calendar
          value={selectedDate}
          onChange={handleDateChange}
          calendarType="ISO 8601"
        />
        <button className="save-button" onClick={handleAddEvent}>
          Agregar Guardia
        </button>

        {events.map((event) => (
          <div key={event.date.toString()} className="event">
            <h5>Guardia creada por: {user.username}</h5>
            <h6>Título: {event.title}</h6>
            <br />
            <h6>Fecha: {event.date.toLocaleDateString()}</h6>
            <h6>Hora: {}</h6>
            <h6>Descripción:</h6>
            <p className="event-description">
              {event.description}
            </p>
          </div>
        ))}

        {showModal && (
          <div className="modal">
            <div className="modal-content">
              <h3>Agrega Guardia</h3>
              <hr />

              <div className="group">
                <h6>
                  <b>Título</b>
                </h6>
                <input
                  type="text"
                  className="inText"
                  placeholder="Nueva guardia"
                  value={eventTitle}
                  onChange={e => setEventTitle(e.target.value)}
                />

                <br />
                <h6>
                  <b>Descripción</b>
                </h6>
                <textarea
                  type="text"
                  className="inText"
                  rows={3}
                  value={eventText}
                  onChange={e => setEventText(e.target.value)}
                />

                <br />
                <h6>
                  <b>Seleccionar Turno</b>
                </h6>
                <select 
                  onChange={handleOptionChange}
                  value={selectedShift}
                  className="inText"
                  disabled={isChecked}
                >
                <option value="" disabled >Seleccionar turno</option>  
                <option value="08:00-20:00">Mañana -- (08:00 a 20:00)</option>  
                <option value="20:00-08:00">Noche -- (20:00 a 08:00)</option>  
                </select>
                
                <br />
                <br />
                <div className="row">
                  <h6 className="col col-8">
                    Seleccionar guardia completa (24 horas)
                  </h6>
                  <input
                    type="checkbox"
                    className="col col-4 check"
                    checked={isChecked}
                    value={eventText}
                    onChange={handleCheckboxChange}
                  />
                </div>
                <br />

                <div className="modal-buttons ">
                  <button onClick={handleSaveEvent} className="save-button">
                    Guardar
                  </button>
                  <button onClick={handleModalClose} className="cancel-button">
                    Cancel
                  </button>
                </div>
                <br />

              </div>
            </div>
          </div>
        )}
      </div>
    </div>
  );
};
