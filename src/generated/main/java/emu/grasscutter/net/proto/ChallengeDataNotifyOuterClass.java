// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: ChallengeDataNotify.proto

package emu.grasscutter.net.proto;

public final class ChallengeDataNotifyOuterClass {
  private ChallengeDataNotifyOuterClass() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  public interface ChallengeDataNotifyOrBuilder extends
      // @@protoc_insertion_point(interface_extends:ChallengeDataNotify)
      com.google.protobuf.MessageOrBuilder {

    /**
     * <code>uint32 value = 8;</code>
     * @return The value.
     */
    int getValue();

    /**
     * <code>uint32 challenge_index = 2;</code>
     * @return The challengeIndex.
     */
    int getChallengeIndex();

    /**
     * <code>uint32 param_index = 9;</code>
     * @return The paramIndex.
     */
    int getParamIndex();
  }
  /**
   * <pre>
   * CmdId: 953
   * EnetChannelId: 0
   * EnetIsReliable: false
   * IsAllowClient: true
   * </pre>
   *
   * Protobuf type {@code ChallengeDataNotify}
   */
  public static final class ChallengeDataNotify extends
      com.google.protobuf.GeneratedMessageV3 implements
      // @@protoc_insertion_point(message_implements:ChallengeDataNotify)
      ChallengeDataNotifyOrBuilder {
  private static final long serialVersionUID = 0L;
    // Use ChallengeDataNotify.newBuilder() to construct.
    private ChallengeDataNotify(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
      super(builder);
    }
    private ChallengeDataNotify() {
    }

    @java.lang.Override
    @SuppressWarnings({"unused"})
    protected java.lang.Object newInstance(
        UnusedPrivateParameter unused) {
      return new ChallengeDataNotify();
    }

    @java.lang.Override
    public final com.google.protobuf.UnknownFieldSet
    getUnknownFields() {
      return this.unknownFields;
    }
    private ChallengeDataNotify(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      this();
      if (extensionRegistry == null) {
        throw new java.lang.NullPointerException();
      }
      com.google.protobuf.UnknownFieldSet.Builder unknownFields =
          com.google.protobuf.UnknownFieldSet.newBuilder();
      try {
        boolean done = false;
        while (!done) {
          int tag = input.readTag();
          switch (tag) {
            case 0:
              done = true;
              break;
            case 16: {

              challengeIndex_ = input.readUInt32();
              break;
            }
            case 64: {

              value_ = input.readUInt32();
              break;
            }
            case 72: {

              paramIndex_ = input.readUInt32();
              break;
            }
            default: {
              if (!parseUnknownField(
                  input, unknownFields, extensionRegistry, tag)) {
                done = true;
              }
              break;
            }
          }
        }
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        throw e.setUnfinishedMessage(this);
      } catch (java.io.IOException e) {
        throw new com.google.protobuf.InvalidProtocolBufferException(
            e).setUnfinishedMessage(this);
      } finally {
        this.unknownFields = unknownFields.build();
        makeExtensionsImmutable();
      }
    }
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return emu.grasscutter.net.proto.ChallengeDataNotifyOuterClass.internal_static_ChallengeDataNotify_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return emu.grasscutter.net.proto.ChallengeDataNotifyOuterClass.internal_static_ChallengeDataNotify_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              emu.grasscutter.net.proto.ChallengeDataNotifyOuterClass.ChallengeDataNotify.class, emu.grasscutter.net.proto.ChallengeDataNotifyOuterClass.ChallengeDataNotify.Builder.class);
    }

    public static final int VALUE_FIELD_NUMBER = 8;
    private int value_;
    /**
     * <code>uint32 value = 8;</code>
     * @return The value.
     */
    @java.lang.Override
    public int getValue() {
      return value_;
    }

    public static final int CHALLENGE_INDEX_FIELD_NUMBER = 2;
    private int challengeIndex_;
    /**
     * <code>uint32 challenge_index = 2;</code>
     * @return The challengeIndex.
     */
    @java.lang.Override
    public int getChallengeIndex() {
      return challengeIndex_;
    }

    public static final int PARAM_INDEX_FIELD_NUMBER = 9;
    private int paramIndex_;
    /**
     * <code>uint32 param_index = 9;</code>
     * @return The paramIndex.
     */
    @java.lang.Override
    public int getParamIndex() {
      return paramIndex_;
    }

    private byte memoizedIsInitialized = -1;
    @java.lang.Override
    public final boolean isInitialized() {
      byte isInitialized = memoizedIsInitialized;
      if (isInitialized == 1) return true;
      if (isInitialized == 0) return false;

      memoizedIsInitialized = 1;
      return true;
    }

    @java.lang.Override
    public void writeTo(com.google.protobuf.CodedOutputStream output)
                        throws java.io.IOException {
      if (challengeIndex_ != 0) {
        output.writeUInt32(2, challengeIndex_);
      }
      if (value_ != 0) {
        output.writeUInt32(8, value_);
      }
      if (paramIndex_ != 0) {
        output.writeUInt32(9, paramIndex_);
      }
      unknownFields.writeTo(output);
    }

    @java.lang.Override
    public int getSerializedSize() {
      int size = memoizedSize;
      if (size != -1) return size;

      size = 0;
      if (challengeIndex_ != 0) {
        size += com.google.protobuf.CodedOutputStream
          .computeUInt32Size(2, challengeIndex_);
      }
      if (value_ != 0) {
        size += com.google.protobuf.CodedOutputStream
          .computeUInt32Size(8, value_);
      }
      if (paramIndex_ != 0) {
        size += com.google.protobuf.CodedOutputStream
          .computeUInt32Size(9, paramIndex_);
      }
      size += unknownFields.getSerializedSize();
      memoizedSize = size;
      return size;
    }

    @java.lang.Override
    public boolean equals(final java.lang.Object obj) {
      if (obj == this) {
       return true;
      }
      if (!(obj instanceof emu.grasscutter.net.proto.ChallengeDataNotifyOuterClass.ChallengeDataNotify)) {
        return super.equals(obj);
      }
      emu.grasscutter.net.proto.ChallengeDataNotifyOuterClass.ChallengeDataNotify other = (emu.grasscutter.net.proto.ChallengeDataNotifyOuterClass.ChallengeDataNotify) obj;

      if (getValue()
          != other.getValue()) return false;
      if (getChallengeIndex()
          != other.getChallengeIndex()) return false;
      if (getParamIndex()
          != other.getParamIndex()) return false;
      if (!unknownFields.equals(other.unknownFields)) return false;
      return true;
    }

    @java.lang.Override
    public int hashCode() {
      if (memoizedHashCode != 0) {
        return memoizedHashCode;
      }
      int hash = 41;
      hash = (19 * hash) + getDescriptor().hashCode();
      hash = (37 * hash) + VALUE_FIELD_NUMBER;
      hash = (53 * hash) + getValue();
      hash = (37 * hash) + CHALLENGE_INDEX_FIELD_NUMBER;
      hash = (53 * hash) + getChallengeIndex();
      hash = (37 * hash) + PARAM_INDEX_FIELD_NUMBER;
      hash = (53 * hash) + getParamIndex();
      hash = (29 * hash) + unknownFields.hashCode();
      memoizedHashCode = hash;
      return hash;
    }

    public static emu.grasscutter.net.proto.ChallengeDataNotifyOuterClass.ChallengeDataNotify parseFrom(
        java.nio.ByteBuffer data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static emu.grasscutter.net.proto.ChallengeDataNotifyOuterClass.ChallengeDataNotify parseFrom(
        java.nio.ByteBuffer data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static emu.grasscutter.net.proto.ChallengeDataNotifyOuterClass.ChallengeDataNotify parseFrom(
        com.google.protobuf.ByteString data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static emu.grasscutter.net.proto.ChallengeDataNotifyOuterClass.ChallengeDataNotify parseFrom(
        com.google.protobuf.ByteString data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static emu.grasscutter.net.proto.ChallengeDataNotifyOuterClass.ChallengeDataNotify parseFrom(byte[] data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static emu.grasscutter.net.proto.ChallengeDataNotifyOuterClass.ChallengeDataNotify parseFrom(
        byte[] data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static emu.grasscutter.net.proto.ChallengeDataNotifyOuterClass.ChallengeDataNotify parseFrom(java.io.InputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input);
    }
    public static emu.grasscutter.net.proto.ChallengeDataNotifyOuterClass.ChallengeDataNotify parseFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input, extensionRegistry);
    }
    public static emu.grasscutter.net.proto.ChallengeDataNotifyOuterClass.ChallengeDataNotify parseDelimitedFrom(java.io.InputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseDelimitedWithIOException(PARSER, input);
    }
    public static emu.grasscutter.net.proto.ChallengeDataNotifyOuterClass.ChallengeDataNotify parseDelimitedFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }
    public static emu.grasscutter.net.proto.ChallengeDataNotifyOuterClass.ChallengeDataNotify parseFrom(
        com.google.protobuf.CodedInputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input);
    }
    public static emu.grasscutter.net.proto.ChallengeDataNotifyOuterClass.ChallengeDataNotify parseFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input, extensionRegistry);
    }

    @java.lang.Override
    public Builder newBuilderForType() { return newBuilder(); }
    public static Builder newBuilder() {
      return DEFAULT_INSTANCE.toBuilder();
    }
    public static Builder newBuilder(emu.grasscutter.net.proto.ChallengeDataNotifyOuterClass.ChallengeDataNotify prototype) {
      return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
    }
    @java.lang.Override
    public Builder toBuilder() {
      return this == DEFAULT_INSTANCE
          ? new Builder() : new Builder().mergeFrom(this);
    }

    @java.lang.Override
    protected Builder newBuilderForType(
        com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
      Builder builder = new Builder(parent);
      return builder;
    }
    /**
     * <pre>
     * CmdId: 953
     * EnetChannelId: 0
     * EnetIsReliable: false
     * IsAllowClient: true
     * </pre>
     *
     * Protobuf type {@code ChallengeDataNotify}
     */
    public static final class Builder extends
        com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
        // @@protoc_insertion_point(builder_implements:ChallengeDataNotify)
        emu.grasscutter.net.proto.ChallengeDataNotifyOuterClass.ChallengeDataNotifyOrBuilder {
      public static final com.google.protobuf.Descriptors.Descriptor
          getDescriptor() {
        return emu.grasscutter.net.proto.ChallengeDataNotifyOuterClass.internal_static_ChallengeDataNotify_descriptor;
      }

      @java.lang.Override
      protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
          internalGetFieldAccessorTable() {
        return emu.grasscutter.net.proto.ChallengeDataNotifyOuterClass.internal_static_ChallengeDataNotify_fieldAccessorTable
            .ensureFieldAccessorsInitialized(
                emu.grasscutter.net.proto.ChallengeDataNotifyOuterClass.ChallengeDataNotify.class, emu.grasscutter.net.proto.ChallengeDataNotifyOuterClass.ChallengeDataNotify.Builder.class);
      }

      // Construct using emu.grasscutter.net.proto.ChallengeDataNotifyOuterClass.ChallengeDataNotify.newBuilder()
      private Builder() {
        maybeForceBuilderInitialization();
      }

      private Builder(
          com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
        super(parent);
        maybeForceBuilderInitialization();
      }
      private void maybeForceBuilderInitialization() {
        if (com.google.protobuf.GeneratedMessageV3
                .alwaysUseFieldBuilders) {
        }
      }
      @java.lang.Override
      public Builder clear() {
        super.clear();
        value_ = 0;

        challengeIndex_ = 0;

        paramIndex_ = 0;

        return this;
      }

      @java.lang.Override
      public com.google.protobuf.Descriptors.Descriptor
          getDescriptorForType() {
        return emu.grasscutter.net.proto.ChallengeDataNotifyOuterClass.internal_static_ChallengeDataNotify_descriptor;
      }

      @java.lang.Override
      public emu.grasscutter.net.proto.ChallengeDataNotifyOuterClass.ChallengeDataNotify getDefaultInstanceForType() {
        return emu.grasscutter.net.proto.ChallengeDataNotifyOuterClass.ChallengeDataNotify.getDefaultInstance();
      }

      @java.lang.Override
      public emu.grasscutter.net.proto.ChallengeDataNotifyOuterClass.ChallengeDataNotify build() {
        emu.grasscutter.net.proto.ChallengeDataNotifyOuterClass.ChallengeDataNotify result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(result);
        }
        return result;
      }

      @java.lang.Override
      public emu.grasscutter.net.proto.ChallengeDataNotifyOuterClass.ChallengeDataNotify buildPartial() {
        emu.grasscutter.net.proto.ChallengeDataNotifyOuterClass.ChallengeDataNotify result = new emu.grasscutter.net.proto.ChallengeDataNotifyOuterClass.ChallengeDataNotify(this);
        result.value_ = value_;
        result.challengeIndex_ = challengeIndex_;
        result.paramIndex_ = paramIndex_;
        onBuilt();
        return result;
      }

      @java.lang.Override
      public Builder clone() {
        return super.clone();
      }
      @java.lang.Override
      public Builder setField(
          com.google.protobuf.Descriptors.FieldDescriptor field,
          java.lang.Object value) {
        return super.setField(field, value);
      }
      @java.lang.Override
      public Builder clearField(
          com.google.protobuf.Descriptors.FieldDescriptor field) {
        return super.clearField(field);
      }
      @java.lang.Override
      public Builder clearOneof(
          com.google.protobuf.Descriptors.OneofDescriptor oneof) {
        return super.clearOneof(oneof);
      }
      @java.lang.Override
      public Builder setRepeatedField(
          com.google.protobuf.Descriptors.FieldDescriptor field,
          int index, java.lang.Object value) {
        return super.setRepeatedField(field, index, value);
      }
      @java.lang.Override
      public Builder addRepeatedField(
          com.google.protobuf.Descriptors.FieldDescriptor field,
          java.lang.Object value) {
        return super.addRepeatedField(field, value);
      }
      @java.lang.Override
      public Builder mergeFrom(com.google.protobuf.Message other) {
        if (other instanceof emu.grasscutter.net.proto.ChallengeDataNotifyOuterClass.ChallengeDataNotify) {
          return mergeFrom((emu.grasscutter.net.proto.ChallengeDataNotifyOuterClass.ChallengeDataNotify)other);
        } else {
          super.mergeFrom(other);
          return this;
        }
      }

      public Builder mergeFrom(emu.grasscutter.net.proto.ChallengeDataNotifyOuterClass.ChallengeDataNotify other) {
        if (other == emu.grasscutter.net.proto.ChallengeDataNotifyOuterClass.ChallengeDataNotify.getDefaultInstance()) return this;
        if (other.getValue() != 0) {
          setValue(other.getValue());
        }
        if (other.getChallengeIndex() != 0) {
          setChallengeIndex(other.getChallengeIndex());
        }
        if (other.getParamIndex() != 0) {
          setParamIndex(other.getParamIndex());
        }
        this.mergeUnknownFields(other.unknownFields);
        onChanged();
        return this;
      }

      @java.lang.Override
      public final boolean isInitialized() {
        return true;
      }

      @java.lang.Override
      public Builder mergeFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws java.io.IOException {
        emu.grasscutter.net.proto.ChallengeDataNotifyOuterClass.ChallengeDataNotify parsedMessage = null;
        try {
          parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
        } catch (com.google.protobuf.InvalidProtocolBufferException e) {
          parsedMessage = (emu.grasscutter.net.proto.ChallengeDataNotifyOuterClass.ChallengeDataNotify) e.getUnfinishedMessage();
          throw e.unwrapIOException();
        } finally {
          if (parsedMessage != null) {
            mergeFrom(parsedMessage);
          }
        }
        return this;
      }

      private int value_ ;
      /**
       * <code>uint32 value = 8;</code>
       * @return The value.
       */
      @java.lang.Override
      public int getValue() {
        return value_;
      }
      /**
       * <code>uint32 value = 8;</code>
       * @param value The value to set.
       * @return This builder for chaining.
       */
      public Builder setValue(int value) {
        
        value_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>uint32 value = 8;</code>
       * @return This builder for chaining.
       */
      public Builder clearValue() {
        
        value_ = 0;
        onChanged();
        return this;
      }

      private int challengeIndex_ ;
      /**
       * <code>uint32 challenge_index = 2;</code>
       * @return The challengeIndex.
       */
      @java.lang.Override
      public int getChallengeIndex() {
        return challengeIndex_;
      }
      /**
       * <code>uint32 challenge_index = 2;</code>
       * @param value The challengeIndex to set.
       * @return This builder for chaining.
       */
      public Builder setChallengeIndex(int value) {
        
        challengeIndex_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>uint32 challenge_index = 2;</code>
       * @return This builder for chaining.
       */
      public Builder clearChallengeIndex() {
        
        challengeIndex_ = 0;
        onChanged();
        return this;
      }

      private int paramIndex_ ;
      /**
       * <code>uint32 param_index = 9;</code>
       * @return The paramIndex.
       */
      @java.lang.Override
      public int getParamIndex() {
        return paramIndex_;
      }
      /**
       * <code>uint32 param_index = 9;</code>
       * @param value The paramIndex to set.
       * @return This builder for chaining.
       */
      public Builder setParamIndex(int value) {
        
        paramIndex_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>uint32 param_index = 9;</code>
       * @return This builder for chaining.
       */
      public Builder clearParamIndex() {
        
        paramIndex_ = 0;
        onChanged();
        return this;
      }
      @java.lang.Override
      public final Builder setUnknownFields(
          final com.google.protobuf.UnknownFieldSet unknownFields) {
        return super.setUnknownFields(unknownFields);
      }

      @java.lang.Override
      public final Builder mergeUnknownFields(
          final com.google.protobuf.UnknownFieldSet unknownFields) {
        return super.mergeUnknownFields(unknownFields);
      }


      // @@protoc_insertion_point(builder_scope:ChallengeDataNotify)
    }

    // @@protoc_insertion_point(class_scope:ChallengeDataNotify)
    private static final emu.grasscutter.net.proto.ChallengeDataNotifyOuterClass.ChallengeDataNotify DEFAULT_INSTANCE;
    static {
      DEFAULT_INSTANCE = new emu.grasscutter.net.proto.ChallengeDataNotifyOuterClass.ChallengeDataNotify();
    }

    public static emu.grasscutter.net.proto.ChallengeDataNotifyOuterClass.ChallengeDataNotify getDefaultInstance() {
      return DEFAULT_INSTANCE;
    }

    private static final com.google.protobuf.Parser<ChallengeDataNotify>
        PARSER = new com.google.protobuf.AbstractParser<ChallengeDataNotify>() {
      @java.lang.Override
      public ChallengeDataNotify parsePartialFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws com.google.protobuf.InvalidProtocolBufferException {
        return new ChallengeDataNotify(input, extensionRegistry);
      }
    };

    public static com.google.protobuf.Parser<ChallengeDataNotify> parser() {
      return PARSER;
    }

    @java.lang.Override
    public com.google.protobuf.Parser<ChallengeDataNotify> getParserForType() {
      return PARSER;
    }

    @java.lang.Override
    public emu.grasscutter.net.proto.ChallengeDataNotifyOuterClass.ChallengeDataNotify getDefaultInstanceForType() {
      return DEFAULT_INSTANCE;
    }

  }

  private static final com.google.protobuf.Descriptors.Descriptor
    internal_static_ChallengeDataNotify_descriptor;
  private static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_ChallengeDataNotify_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\031ChallengeDataNotify.proto\"R\n\023Challenge" +
      "DataNotify\022\r\n\005value\030\010 \001(\r\022\027\n\017challenge_i" +
      "ndex\030\002 \001(\r\022\023\n\013param_index\030\t \001(\rB\033\n\031emu.g" +
      "rasscutter.net.protob\006proto3"
    };
    descriptor = com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
        });
    internal_static_ChallengeDataNotify_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_ChallengeDataNotify_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_ChallengeDataNotify_descriptor,
        new java.lang.String[] { "Value", "ChallengeIndex", "ParamIndex", });
  }

  // @@protoc_insertion_point(outer_class_scope)
}
