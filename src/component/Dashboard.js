import React, { useEffect, useState } from 'react';
import axios from 'axios';
import './Dashboard.css';

export const Dashboard = () => {
    const [showData, setShowData] = useState([]);
    const [printData, setPrintData] = useState([]);
    const [shows, setShows] = useState(null);
    const [seat, setSeat] = useState([]);
    const [selectedSeats, setSelectedSeats] = useState({});

    useEffect(() => {
        fetchData();
    }, []);

    const fetchData = async () => {
        try {
            const response = await axios.get("http://localhost:8080/api/shows");
            setShowData(response.data);
        } catch (error) {
            console.error('Error fetching data:', error);
        }
    };

    const handlePrintData = async () => {
        try {
            const response = await axios.post(`http://localhost:8080/api/shows/${parseInt(shows)}/book`, seat);
            alert(response.data?.message);
            setPrintData(response.data);
        } catch (error) {
            console.error('Axios error:', error);
        }
    };

    const bookSeat = (showId, seat) => {
        setShows(showId);
        setSeat(prevRecords => [...prevRecords, seat]);
        setSelectedSeats(prevSelectedSeats => ({
            ...prevSelectedSeats,
            [showId]: [...(prevSelectedSeats[showId] || []), seat]
        }));
    };

    const handlePrint = () => {
        const printContents = document.getElementById('printableArea').innerHTML;
        const originalContents = document.body.innerHTML;

        document.body.innerHTML = printContents;
        window.print();
        document.body.innerHTML = originalContents;

        window.location.reload();
    };

    return (
        <div className="dashboard">
            <div className="container">
                
                {showData.map(show => (
                    <div className="box">
                        <div key={show.showId} className="show-box">
                            <h2 className='show-title'>Shows: {show.audi}</h2>
                            <p className='show-info'>Available seats:</p>
                            <div className="seat-container">
                                {show.availableSeats.map(seat => (
                                    <button
                                        key={seat}
                                        onClick={() => bookSeat(show.showId, seat)}
                                        disabled={show.bookedSeats.includes(seat)}
                                        className={selectedSeats[show.showId] && selectedSeats[show.showId].includes(seat) ? "seat-button selected" : "seat-button"}
                                    >
                                        {seat}
                                    </button>
                                ))}
                            </div>
                        </div>
                    </div>
                ))}

            </div>


            <button onClick={handlePrintData} className="action-button">
                Book Ticket
            </button>

            {printData?.ticket !== undefined && printData?.ticket !== null && (
                <>
                    <h1>{printData?.message}</h1>
                    <div className="ticket-details" id="printableArea">
                        <div>
                            <p><strong>Booked Seats: {seat.join(', ')}</strong></p>
                            <p><strong>Show ID:</strong> {printData?.ticket.showId === 1 ? "audi 1" : printData?.ticket.showId === 2 ? "audi 2" : "audi 3"}</p>
                            <p><strong>Subtotal:</strong> {printData?.ticket.subtotal}</p>
                            <p><strong>Service Tax:</strong> {printData?.ticket.serviceTax.toFixed(2)}</p>
                            <p><strong>Swachh Bharat Cess:</strong> {printData?.ticket.swachhBharatCess.toFixed(2)}</p>
                            <p><strong>Krishi Kalyan Cess:</strong> {printData?.ticket.krishiKalyanCess.toFixed(2)}</p>
                            <p><strong>Total:</strong> {printData?.ticket.total.toFixed(2)}</p>
                        </div>
                    </div>
                    <button onClick={handlePrint} className="action-button">Print Ticket</button>
                </>
            )}
        </div>
    );
};
