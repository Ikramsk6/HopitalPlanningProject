import React from "react";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import HomePage from "./pages/HomePage";
import ScheduleVisualizationPage from "./pages/ScheduleVisualizationPage";
import ExistantSchedulePage from "./pages/ExistantSchedulePage";
import NewSchedulePage from "./pages/NewSchedulePage";
import ShiftCreationPage from "./pages/ShiftCreationPage";
import EmployeeSetupPage from "./pages/EmployeeSetupPage";
import ForbiddenShiftsPage from "./pages/ForbiddenShiftsPage";
import NeedsSetupPage from "./pages/NeedsSetupPage";


const App = () => (
  <Router>
    <Routes>
      <Route path="/" element={<HomePage />} />
      <Route path="/existant-schedule" element={<ExistantSchedulePage />} />
      <Route path="/new-schedule" element={<NewSchedulePage />} />
      <Route path="/schedule-visualization" element={<ScheduleVisualizationPage />} />
      <Route path="/shift-creation" element={<ShiftCreationPage />} />
      <Route path="/employee-setup" element={<EmployeeSetupPage />} />
      <Route path="/forbidden-shifts" element={<ForbiddenShiftsPage />} />
      <Route path="/needs-setup" element={<NeedsSetupPage />} />
    </Routes>
  </Router>
);

export default App;
