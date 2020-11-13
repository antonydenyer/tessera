package com.quorum.tessera.recovery.workflow;

import com.quorum.tessera.data.EncryptedTransactionDAO;
import com.quorum.tessera.data.staging.StagingEntityDAO;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class BatchResendManagerProviderTest {

    @Test
    public void defaultConstructorForCoverage() {
        assertThat(new BatchResendManagerProvider()).isNotNull();
    }

    @Test
    public void provider() {

        try (
            var staticEncryptedTransactionDAO = mockStatic(EncryptedTransactionDAO.class);
            var staticStagingEntityDAO = mockStatic(StagingEntityDAO.class);
            var staticBatchWorkflowFactory = mockStatic(BatchWorkflowFactory.class);
            ) {

            staticEncryptedTransactionDAO.when(EncryptedTransactionDAO::create).thenReturn(mock(EncryptedTransactionDAO.class));
            staticStagingEntityDAO.when(StagingEntityDAO::create).thenReturn(mock(StagingEntityDAO.class));
            staticBatchWorkflowFactory.when(BatchWorkflowFactory::create).thenReturn(mock(BatchWorkflowFactory.class));

            BatchResendManager batchResendManager = BatchResendManagerProvider.provider();
            assertThat(batchResendManager).isNotNull().isExactlyInstanceOf(BatchResendManagerImpl.class);

            staticEncryptedTransactionDAO.verify(EncryptedTransactionDAO::create);
            staticStagingEntityDAO.verify(StagingEntityDAO::create);
            staticBatchWorkflowFactory.verify(BatchWorkflowFactory::create);

            staticEncryptedTransactionDAO.verifyNoMoreInteractions();
            staticStagingEntityDAO.verifyNoMoreInteractions();
            staticBatchWorkflowFactory.verifyNoMoreInteractions();

            assertThat(BatchResendManagerHolder.INSTANCE.getBatchResendManager()).isPresent().containsSame(batchResendManager);

            assertThat(BatchResendManagerProvider.provider()).isSameAs(batchResendManager);

        }

    }

}
