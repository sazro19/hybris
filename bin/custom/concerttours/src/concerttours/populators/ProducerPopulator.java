package concerttours.populators;

import concerttours.data.ProducerData;
import concerttours.model.ProducerModel;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

public class ProducerPopulator implements Populator<ProducerModel, ProducerData> {

    @Override
    public void populate(ProducerModel producerModel, ProducerData producerData) throws ConversionException {
        producerData.setFirstName(producerModel.getFirstName());
        producerData.setLastName(producerModel.getLastName());
    }
}
