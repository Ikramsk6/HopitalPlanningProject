import axios from 'axios';

const API_URL = '/api/solutions';

export const getAllSolutions = () => axios.get(API_URL);
export const createSolution = (solution) => axios.post(API_URL, solution);
