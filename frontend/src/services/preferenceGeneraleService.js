import axios from 'axios';

const API_URL = '/api/preferencesGenerales';

export const getAllPreferences = () => axios.get(API_URL);
export const createPreference = (preference) => axios.post(API_URL, preference);
