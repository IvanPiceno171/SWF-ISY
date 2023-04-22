import React, { useMemo, useCallback } from "react";
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
  const center = useMemo(() => ({ lat: 37.325954, lng: -120.499992 }), []);

  const onMapLoad = useCallback((map) => {
    const bounds = new window.google.maps.LatLngBounds();
    const mercedSW = new window.google.maps.LatLng(37.24, -120.5429);
    const mercedNE = new window.google.maps.LatLng(37.3346, -120.4164);

    bounds.extend(mercedSW);
    bounds.extend(mercedNE);

    map.fitBounds(bounds);
  }, []);

  const options = {
    minZoom: 12,
    maxZoom: 20,
    restriction: {
      latLngBounds: {
        north: 37.3346,
        south: 37.24,
        east: -120.4164,
        west: -120.5429,
      },
      strictBounds: true,
    },
  };

  return (
    <GoogleMap
      zoom={12}
      center={center}
      options={options}
      mapContainerClassName="map-container"
      onClick={(e) => {
        console.log("lat", e.latLng.lat());
        console.log("long", e.latLng.lng());
      }}
      onLoad={onMapLoad}
    >
      <Marker position={center} />
    </GoogleMap>
  );
}
