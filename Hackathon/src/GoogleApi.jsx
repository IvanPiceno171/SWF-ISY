import React, { useMemo, useCallback, useState } from "react";
import {
  GoogleMap,
  useLoadScript,
  Marker,
  Circle,
} from "@react-google-maps/api";
import "./App.css";
import stageHeightSlopes from "./stageHeightSlopes";
import precipitationSlopes from "./precipitationSlopes";

export default function GoogleApi() {
  const { isLoaded } = useLoadScript({
    googleMapsApiKey: "AIzaSyDLorRCUcHdtNsfZAwF1-yYLhdrIFllPXY",
  });

  if (!isLoaded) {
    return <div>Loading...</div>;
  } else {
    return <GoogleMapComponent />;
  }
}

function GoogleMapComponent() {
  const center = useMemo(() => ({ lat: 37.309976, lng: -120.469538 }), []);

  const onMapLoad = useCallback((map) => {
    const bounds = new window.google.maps.LatLngBounds();
    const mercedSW = new window.google.maps.LatLng(37.295495, -120.495064);
    const mercedNE = new window.google.maps.LatLng(37.322478, -120.443458);

    bounds.extend(mercedSW);
    bounds.extend(mercedNE);

    map.fitBounds(bounds);
  }, []);

  const options = {
    minZoom: 0,
    maxZoom: 15,
    restriction: {
      latLngBounds: {
        north: 37.335947,
        south: 37.287567,
        east: -120.4164,
        west: -120.5429,
      },
      strictBounds: true,
    },
    fullscreenControl: false,
    streetViewControl: false,
    gestureHandling: "none",
    styles: [
      {
        featureType: "poi",
        elementType: "labels",
        stylers: [
          {
            visibility: "off",
          },
        ],
      },
    ],
  };

  const [showText, setShowText] = useState(false);
  const [coords, setCoords] = useState({ lat: null, lng: null });

  const circleOptions = {
    strokeColor: "#FF0000",
    strokeOpacity: 0.8,
    strokeWeight: 2,
    fillOpacity: 0.3,
    fillColor: "#FF0000",
  };

  const circle1 = {
    center: { lat: 37.3105, lng: -120.46965 },
    radius: 700, // in meters
  };

  const circle2 = {
    center: { lat: 37.3125, lng: -120.4825 },
    radius: 700, // in meters
  };

  const [showOverlay, setShowOverlay] = useState(false);
  const showFlood = () => {
    setShowOverlay((bool) => !bool);
  };

  const handleMapClick = (e) => {
    setShowText(true);
    setCoords({ lat: e.latLng.lat(), lng: e.latLng.lng() });
    console.log(showText);
  };

  const [code1Expanded, setCode1Expanded] = useState(false);
  const [code2Expanded, setCode2Expanded] = useState(false);

  const toggleCode1 = () => {
    setCode1Expanded(!code1Expanded);
  };
  const toggleCode2 = () => {
    setCode2Expanded(!code2Expanded);
  };

  return (
    <>
      <GoogleMap
        zoom={12}
        center={center}
        options={options}
        mapContainerClassName="map-container"
        onClick={(e) => {
          console.log("lat", e.latLng.lat());
          console.log("long", e.latLng.lng());
          {
            handleMapClick(e);
          }
        }}
        onLoad={onMapLoad}
      >
        <Marker position={center} />
        {showOverlay && (
          <>
            <Circle
              key="circle1"
              center={circle1.center}
              radius={circle1.radius}
              options={circleOptions}
            />
            <Circle
              key="circle2"
              center={circle2.center}
              radius={circle2.radius}
              options={circleOptions}
            />
          </>
        )}
      </GoogleMap>
      <button className="flood-btn" onClick={showFlood}>
        Show Possible Flooded Area
      </button>
      {showText && (
        <>
          <p>
            Latitude: {coords.lat.toFixed(4)}, Longitude:{" "}
            {coords.lng.toFixed(4)}
          </p>
          <p className="info">
            The risk of a future flood from Bear Creek to your location is:
          </p>
          <p className="risk">Moderate</p>
          <p className="info2">Here's why we think the risk is moderate:</p>

          <div className="data-wrapper">
            <p className="slope-description">
              Here are the calculated slopes of the Stage Height and Precipition
              between 1-Dec-22 to 23-Feb-23
            </p>
            <div className="flex-container">
              <code className="code-box" onClick={toggleCode1}>
                {code1Expanded
                  ? stageHeightSlopes
                  : "Click here to view the Java code for the Stage Height slope calculations"}
              </code>
              <code className="code-box" onClick={toggleCode2}>
                {code2Expanded
                  ? precipitationSlopes
                  : "Click here to view the Java code for the Precipitation slope calculations"}
              </code>
            </div>
            <div className="img-wrapper">
              <img
                src="public/stage-height-slopes.png"
                className="slope-images"
              />
              <img
                src="public/precipitation-slopes.png"
                className="slope-images"
              />
            </div>
          </div>

          {/* <div className="pcc-wrapper">
            <p className="pcc-description">
              Then we analyzed the slope data using the{" "}
              <span className="highlight">
                Pearson correlation coefficient formula
              </span>
              . The Pearson correlation coefficient formula is a measure of
              linear correlation between two sets of data.
              <br />
              <br /> In this case, we have 2 sets of slope data we want to
              analyze using PCC.
            </p>
            <img src="public/pcc.png" className="image-pcc" />
            <p className="pcc-description">
              When we input the data into PCC we get a coefficient of:{" "}
              <span className="highlight">0.445</span>
              <br />
              <br />
              This indicates that there is a{" "}
              <span className="highlight">strong correlation</span> between the
              slopes of the Stage Height and Precipitation.
            </p>
          </div> */}
        </>
      )}
    </>
  );
}
