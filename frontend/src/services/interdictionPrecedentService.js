import axios from 'axios';

const API_URL = '/api/interdictionsPrecedents';

export const getAllInterdictions = () => axios.get(API_URL);
export const createInterdiction = (interdiction) => axios.post(API_URL, interdiction);
