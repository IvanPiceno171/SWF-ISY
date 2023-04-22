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
  const center = useMemo(() => ({ lat: 37.325954, lng: -120.499992}), []);

  return (
    <GoogleMap zoom={12} center={center} mapContainerClassName="map-container"
      onClick={(e)=>{
        console.log("lat", e.latLng.lat())
        console.log("long", e.latLng.lng())
      }}
    
    >
      <Marker position={center} />
    </GoogleMap>
  );
}
