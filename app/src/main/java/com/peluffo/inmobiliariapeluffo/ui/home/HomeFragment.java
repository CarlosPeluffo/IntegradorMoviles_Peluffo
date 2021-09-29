package com.peluffo.inmobiliariapeluffo.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.peluffo.inmobiliariapeluffo.R;
import com.peluffo.inmobiliariapeluffo.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment {
    private LatLng inmobiliaria = new LatLng(-33.687238, -65.475492);
    private GoogleMap map;
    private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SupportMapFragment mapFragment =
                (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.mapa);
        if (mapFragment != null) {
            mapFragment.getMapAsync(new MapaActual());
        }
    }

    @Override
    public void onStart() {
        super.onStart();
    }

   private class MapaActual implements OnMapReadyCallback {
        @Override
        public void onMapReady(GoogleMap mapa) {
            map = mapa;
            map.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
            CameraPosition camPos = new CameraPosition.Builder()
                    .target(inmobiliaria)
                    .zoom(19)
                    .bearing(50)
                    .tilt(50)
                    .build();
            CameraUpdate cameraUpdate = CameraUpdateFactory.newCameraPosition(camPos);
            MarkerOptions marcadorInmueble = new MarkerOptions().position(inmobiliaria);
            marcadorInmueble.title("Inmobiliaria Peluffo");

            mapa.animateCamera(cameraUpdate);
            mapa.addMarker(marcadorInmueble);
        }
    }
}