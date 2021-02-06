package com.quorum.tessera.privacygroup;

import com.quorum.tessera.data.PrivacyGroupDAO;
import com.quorum.tessera.enclave.Enclave;
import com.quorum.tessera.privacygroup.publish.BatchPrivacyGroupPublisher;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockStatic;

public class PrivacyGroupManagerProviderTest {

    @Test
    public void provider() {

        try(var enclaveMockedStatic = mockStatic(Enclave.class);
            var privacyGroupDAOMockStatic = mockStatic(PrivacyGroupDAO.class);
            var batchPrivacyGroupPublisherMockedStatic = mockStatic(BatchPrivacyGroupPublisher.class)
        ) {

            enclaveMockedStatic.when(Enclave::create)
                .thenReturn(mock(Enclave.class));

            privacyGroupDAOMockStatic.when(PrivacyGroupDAO::create)
                .thenReturn(mock(PrivacyGroupDAO.class));

            batchPrivacyGroupPublisherMockedStatic.when(BatchPrivacyGroupPublisher::create)
                .thenReturn(mock(BatchPrivacyGroupPublisher.class));

            PrivacyGroupManager result = PrivacyGroupManagerProvider.provider();

            assertThat(result).isNotNull();

            enclaveMockedStatic.verify(Enclave::create);
            enclaveMockedStatic.verifyNoMoreInteractions();

            privacyGroupDAOMockStatic.verify(PrivacyGroupDAO::create);
            privacyGroupDAOMockStatic.verifyNoMoreInteractions();

            batchPrivacyGroupPublisherMockedStatic.verify(BatchPrivacyGroupPublisher::create);
            batchPrivacyGroupPublisherMockedStatic.verifyNoMoreInteractions();

        }
    }

}
