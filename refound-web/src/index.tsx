import React from 'react';
import ReactDOM from 'react-dom/client';
import Routers from './router';

const root = ReactDOM.createRoot(
  document.getElementById('root') as HTMLElement
);
root.render(
  <React.StrictMode>
    <Routers />
  </React.StrictMode>
);
