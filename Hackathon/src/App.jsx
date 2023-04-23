import GoogleApi from "./GoogleApi.jsx";
import "./App.css";

function App() {
  return (
    <div className="wrapper">
      <h1 className="title">The Flood Risk Helper</h1>
      <p className="description">
        Click on a location near Bear Creek to understand the risk of a flood
        occuring.
      </p>
      <GoogleApi></GoogleApi>
    </div>
  );
}

export default App;
