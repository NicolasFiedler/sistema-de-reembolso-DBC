import { legacy_createStore, compose } from 'redux';
import rootReducer from './reducers';

//#region Redux DevTools
declare global {
  interface Window {
    __REDUX_DEVTOOLS_EXTENSION_COMPOSE__?: typeof compose;
  }
}
const composeEnhancers = window.__REDUX_DEVTOOLS_EXTENSION_COMPOSE__ || compose;
//#endregion Redux DevTools

const store = legacy_createStore(rootReducer, composeEnhancers());

export default store;
