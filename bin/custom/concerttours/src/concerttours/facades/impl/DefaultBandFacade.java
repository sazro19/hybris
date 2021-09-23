package concerttours.facades.impl;

import concerttours.data.BandData;
import concerttours.data.TourSummaryData;
import concerttours.enums.MusicType;
import concerttours.facades.BandFacade;
import concerttours.model.BandModel;
import concerttours.service.BandService;
import de.hybris.platform.core.model.product.ProductModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Component(value = "bandFacade")
public class DefaultBandFacade implements BandFacade {

    @Autowired
    private BandService bandService;

    @Override
    public List<BandData> getBands() {
        final List<BandModel> bandModels = bandService.getBands();
        final List<BandData> bandFacadeData = new ArrayList<>();
        for (final BandModel sm : bandModels) {
            final BandData sfd = new BandData();
            sfd.setId(sm.getCode());
            sfd.setName(sm.getName());
            sfd.setDescription(sm.getHistory());
            sfd.setAlbumsSold(sm.getAlbumSales());
            bandFacadeData.add(sfd);
        }
        return bandFacadeData;
    }

    @Override
    public BandData getBand(final String name) {
        if (name == null) {
            throw new IllegalArgumentException("Band name cannot be null");
        }
        final BandModel band = bandService.getBandForCode(name);
        if (band == null) {
            return null;
        }

        final List<String> genres = new ArrayList<>();
        if (band.getTypes() != null) {
            for (final MusicType musicType : band.getTypes()) {
                genres.add(musicType.getCode());
            }
        }
        final List<TourSummaryData> tourHistory = new ArrayList<>();
        if (band.getTours() != null) {
            for (final ProductModel tour : band.getTours()) {
                final TourSummaryData summary = new TourSummaryData();
                summary.setId(tour.getCode());
                summary.setTourName(tour.getName(Locale.ENGLISH));
                summary.setNumberOfConcerts(Integer.toString(tour.getVariants().size()));
                tourHistory.add(summary);
            }
        }

        final BandData bandData = new BandData();
        bandData.setId(band.getCode());
        bandData.setName(band.getName());
        bandData.setAlbumsSold(band.getAlbumSales());
        bandData.setDescription(band.getHistory());
        bandData.setGenres(genres);
        bandData.setTours(tourHistory);
        return bandData;
    }

    public void setBandService(BandService bandService) {
        this.bandService = bandService;
    }
}
