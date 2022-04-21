import React from 'react';
import './NotFoundPage.css';
import { Route, Link } from 'react-router-dom';
import Homepage from '../Homepage';

function NotFound() {
  return (
    <div id='not-found-div' className='container mt-5'>
      <div className='row'>
        <img
          class='col-6'
          src='https://img.freepik.com/free-vector/astronaut-earth-drawing_9044-211.jpg?w=500'
        />
        <div className='col-6'>
          <h1>404</h1>
          <h2>UH OH! You're lost.</h2>
          <p>
            The page you are looking for does not exist. How you got here is a
            mystery. But you can click the button below to go back to the
            homepage.
          </p>
          <Link type='button' class='not-found-home-btn' to='/'>
            HOME
          </Link>
        </div>
        <Route path='/' exact component={Homepage} />
      </div>
    </div>
  );
}
export default NotFound;
