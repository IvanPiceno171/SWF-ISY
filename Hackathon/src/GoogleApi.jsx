import React from "react";
import { useMemo } from 'react'
import { GoogleMap, useLoadScript, Marker } from "@react-google-maps/api";

export default function GoogleApi() {
  const { isLoaded } = useLoadScript({
    googleMapsApiKey: "AIzaSyDLorRCUcHdtNsfZAwF1-yYLhdrIFllPXY",
  });

  if (!isLoaded){

    return <div>Loading...</div>;
  } else{

    return <GoogleMapComponent />;
  }
}

function GoogleMapComponent() {
  const center = useMemo(() => ({ lat: 44, lng: -80 }), []);

  return (
    <GoogleMap zoom={10} center={center} mapContainerClassName="map-container">
      <Marker position={center} />
    </GoogleMap>
  );
}
