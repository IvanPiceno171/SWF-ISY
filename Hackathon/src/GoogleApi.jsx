import React, { useMemo, useCallback, useState } from "react";
import { GoogleMap, useLoadScript, Marker } from "@react-google-maps/api";

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

  const handleMapClick = (e) => {
    setShowText(true);
    setCoords({ lat: e.latLng.lat(), lng: e.latLng.lng() });
    console.log(showText);
  };

  function checkData(e) {}

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
      </GoogleMap>
      {showText && (
        <p>
          Latitude: {coords.lat.toFixed(4)}, Longitude: {coords.lng.toFixed(4)}
        </p>
      )}
    </>
  );
}
