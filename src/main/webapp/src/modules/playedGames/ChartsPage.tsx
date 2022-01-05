import React from "react";
import { MDBContainer } from "mdbreact";
import { Pie } from "react-chartjs-2";
import {Chart, ArcElement} from 'chart.js'

Chart.register(ArcElement);


const ChartsPage = (props: any) => {
    var frequency = props.frequency;
    // Sample data
    const data = {
        labels: ["Frecuencia 2 jugadores", "Frecuencia 3 jugadores", "Frecuencia 4 jugadores", "Frecuencia 5 jugadores", "Frecuencia 7 jugadores", "Frecuencia 7 jugadores", "Frecuencia 8 jugadores"],
        datasets: [
            {
                label: "Hours Studied in Geeksforgeeks",
                data: [frequency[2],frequency[3],frequency[4],frequency[5],frequency[6],frequency[7],frequency[8]],
                backgroundColor: ["rgba(2, 71, 243, 0.88)", "green", "grey", "rgba(80, 202, 228, 0.88)", "orange", "black", "red"],
            }
        ]
    }
    

    return (
        <MDBContainer>
            <Pie data={data} />
          
        </MDBContainer>
    );
}

export default ChartsPage;