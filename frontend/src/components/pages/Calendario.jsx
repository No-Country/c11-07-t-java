// import "./calendario.css"
// // eslint-disable-next-line no-unused-vars
// import React, { useState } from 'react';
// import DatePicker from 'react-datepicker'
// import 'react-datepicker/dist/react-datepicker.css'

// export const Calendario = () => {
//     const [startDate, setStartDate] = useState(new Date());
    
//   return (
//     <div className="contenedor">
//         <div className="calendario">
//             <DatePicker
//                 selected={startDate}
//                 onChange={(date) => setStartDate(date)}
//                 dateFormat="dd/MM/yyyy"
//                 open={true}
//                 showTimeSelect={false} />

//         </div>
//     </div>


    
    
   
//   );
// };
// eslint-disable-next-line no-unused-vars
import React, { useState } from 'react';
import Calendar from 'react-calendar';
import 'react-calendar/dist/Calendar.css';
import './calendario.css';
import { Header } from '../ui';


export const Calendario = () => {
  const [selectedDate, setSelectedDate] = useState(null);
  const [events, setEvents] = useState([]);
  const [showModal, setShowModal] = useState(false);
  const [eventText, setEventText] = useState('');

  const handleDateChange = date => {
    setSelectedDate(date);
  };

  const handleAddEvent = () => {
    setShowModal(true);
  };

  const handleSaveEvent = () => {
    if (selectedDate && eventText.trim() !== '') {
      const newEvent = {
        date: selectedDate,
        description: eventText
      };

      setEvents([...events, newEvent]);
      setSelectedDate(null);
      setEventText('');
      setShowModal(false);
    }
  };

  const handleModalClose = () => {
    setShowModal(false);
  };

  return (
    <div className="calendario">
    <Header/>
     <div className="container">
      <Calendar
        value={selectedDate}
        onChange={handleDateChange}
        calendarType="ISO 8601"
      />

      <button className="save-button" onClick={handleAddEvent}>Agregar Guardia</button>

      {events.map(event => (
        <div key={event.date.toString()} className="event">
          <p className="event-date">Fecha: {event.date.toLocaleDateString()}</p>
          <p className="event-description">Descripcion: {event.description}</p>
        </div>
      ))}

      {showModal && (
        <div className="modal">
          <div className="modal-content">
            <h3>Agrega Guardia</h3>
            <input
              type="text"
              placeholder="Descripcion"
              value={eventText}
              onChange={e => setEventText(e.target.value)}
            />
            <div className="modal-buttons">
              <button onClick={handleSaveEvent} className="save-button">Guardar</button>
              <button onClick={handleModalClose} className="cancel-button">Cancel</button>
            </div>
          </div>
        </div>
      )}
    </div>
    </div>
   
  );
};
