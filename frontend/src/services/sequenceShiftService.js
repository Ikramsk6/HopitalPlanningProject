import axios from 'axios';

const API_URL = '/api/sequenceShifts';

export const getAllSequences = () => axios.get(API_URL);
export const createSequence = (sequence) => axios.post(API_URL, sequence);
