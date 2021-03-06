/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.dropbox.presto.kafka;

import static com.google.common.base.Preconditions.checkNotNull;

import com.facebook.presto.spi.Connector;
import com.facebook.presto.spi.ConnectorHandleResolver;
import com.facebook.presto.spi.ConnectorMetadata;
import com.facebook.presto.spi.ConnectorOutputHandleResolver;
import com.facebook.presto.spi.ConnectorRecordSetProvider;
import com.facebook.presto.spi.ConnectorRecordSinkProvider;
import com.facebook.presto.spi.ConnectorSplitManager;
import com.google.inject.Inject;

public class KafkaConnector implements Connector
{

    private ConnectorMetadata metadata;
    private ConnectorSplitManager splitManager;
    private ConnectorRecordSetProvider recordSetProvider;
    private ConnectorHandleResolver handleResolver;

    @Inject
    public KafkaConnector(ConnectorMetadata metadata,
        ConnectorSplitManager splitManager,
        ConnectorRecordSetProvider recordSetProvider,
        ConnectorHandleResolver handleResolver)
    {
        construct(metadata, splitManager, recordSetProvider, handleResolver);
    }

    private void construct(ConnectorMetadata metadata,
        ConnectorSplitManager splitManager,
        ConnectorRecordSetProvider recordSetProvider,
        ConnectorHandleResolver handleResolver)
    {
          this.metadata = checkNotNull(metadata, "metadata is null");
          this.splitManager = checkNotNull(splitManager, "splitManager is null");
          this.recordSetProvider = checkNotNull(recordSetProvider,
                  "recordSetProvider is null");
          this.handleResolver = checkNotNull(handleResolver,
                  "handleResolver is null");
    }

    @Override
    public ConnectorMetadata getMetadata()
    {
        return metadata;
    }

    @Override
    public ConnectorSplitManager getSplitManager()
    {
        return splitManager;
    }

    @Override
    public ConnectorRecordSetProvider getRecordSetProvider()
    {
        return recordSetProvider;
    }

    @Override
    public ConnectorHandleResolver getHandleResolver()
    {
        return handleResolver;
    }

    @Override
    public ConnectorRecordSinkProvider getRecordSinkProvider()
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public ConnectorOutputHandleResolver getOutputHandleResolver()
    {
        throw new UnsupportedOperationException();
    }
}
