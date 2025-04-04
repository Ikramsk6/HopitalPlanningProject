import axios from 'axios';

const API_URL = '/api/preference-personnel';

export const getAllPreferencesPersonnel = () => axios.get(API_URL);
export const createPreferencePersonnel = (preference) => axios.post(API_URL, preference);
