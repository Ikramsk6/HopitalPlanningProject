import axios from 'axios';

const API_URL = '/api/shiftsPostes';

export const getAllShifts = () => axios.get(API_URL);
export const createShift = (shift) => axios.post(API_URL, shift);
