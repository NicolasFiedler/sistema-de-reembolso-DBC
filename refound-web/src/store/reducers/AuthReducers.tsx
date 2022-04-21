import { AuthDTO } from '../../models/AuthDTO';

export const INITIAL_STATE = {
  auth: {
    username: '',
    password: '',
  },
};

const authReducer = (state: AuthDTO = INITIAL_STATE) => {
  return state;
};

export default authReducer;
