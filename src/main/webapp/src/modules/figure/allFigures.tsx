import React, { useEffect, useState } from 'react';
import figureImg from "../../images/figures/figures.js";
import figureAPI from './figureAPI.js';

function AllFigures() {
  const [figures, setFigures] = useState<any[]>();

  useEffect(() => {
    figureAPI.getAllFigures()
      .then((fgs: any[]) => setFigures(fgs))
      .catch((err) => console.log(err));
  }, [])

  if (!figures) return <div>Loading...</div>

  let ls = []
  for (let i = 0; i < figures.length; i++) {
    ls[i] = figureImg(figures[i].id)
  }

  return (
    <div>
      {
        ls.map(ls => (
          <div>
            <img src={ls} width='100%' alt="logo" />
          </div>
        ))
      }
    </div>
  );
}

export default AllFigures
