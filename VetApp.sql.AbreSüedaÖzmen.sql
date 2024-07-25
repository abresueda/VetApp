PGDMP                          |            vet    13.15    13.15 *    �           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false            �           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false            �           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false            �           1262    41266    vet    DATABASE     a   CREATE DATABASE vet WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE = 'Turkish_T�rkiye.1254';
    DROP DATABASE vet;
                postgres    false            �            1259    41289    animal    TABLE     +  CREATE TABLE public.animal (
    animal_id bigint NOT NULL,
    breed character varying(255),
    colour character varying(255),
    date_of_birth date,
    gender character varying(255),
    animal_name character varying(255) NOT NULL,
    species character varying(255),
    customer_id bigint
);
    DROP TABLE public.animal;
       public         heap    postgres    false            �            1259    41287    animal_animal_id_seq    SEQUENCE     �   ALTER TABLE public.animal ALTER COLUMN animal_id ADD GENERATED BY DEFAULT AS IDENTITY (
    SEQUENCE NAME public.animal_animal_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);
            public          postgres    false    201            �            1259    41297    animal_vaccine    TABLE     f   CREATE TABLE public.animal_vaccine (
    animal_id bigint NOT NULL,
    vaccine_id bigint NOT NULL
);
 "   DROP TABLE public.animal_vaccine;
       public         heap    postgres    false            �            1259    41302    appointment    TABLE     �   CREATE TABLE public.appointment (
    appointment_id bigint NOT NULL,
    appointment_date timestamp(6) without time zone,
    doctor_id bigint,
    animal_id bigint
);
    DROP TABLE public.appointment;
       public         heap    postgres    false            �            1259    41300    appointment_appointment_id_seq    SEQUENCE     �   ALTER TABLE public.appointment ALTER COLUMN appointment_id ADD GENERATED BY DEFAULT AS IDENTITY (
    SEQUENCE NAME public.appointment_appointment_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);
            public          postgres    false    204            �            1259    41309    available_date    TABLE     }   CREATE TABLE public.available_date (
    available_date_id bigint NOT NULL,
    available_date date,
    doctor_id bigint
);
 "   DROP TABLE public.available_date;
       public         heap    postgres    false            �            1259    41307 $   available_date_available_date_id_seq    SEQUENCE     �   ALTER TABLE public.available_date ALTER COLUMN available_date_id ADD GENERATED BY DEFAULT AS IDENTITY (
    SEQUENCE NAME public.available_date_available_date_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);
            public          postgres    false    206            �            1259    41316    customer    TABLE     *  CREATE TABLE public.customer (
    customer_id bigint NOT NULL,
    customer_address character varying(255),
    customer_city character varying(255),
    customer_mail character varying(255),
    customer_name character varying(255) NOT NULL,
    customer_phone character varying(255) NOT NULL
);
    DROP TABLE public.customer;
       public         heap    postgres    false            �            1259    41314    customer_customer_id_seq    SEQUENCE     �   ALTER TABLE public.customer ALTER COLUMN customer_id ADD GENERATED BY DEFAULT AS IDENTITY (
    SEQUENCE NAME public.customer_customer_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);
            public          postgres    false    208            �            1259    41326    doctor    TABLE     
  CREATE TABLE public.doctor (
    doctor_id bigint NOT NULL,
    doctor_address character varying(255),
    doctor_city character varying(255),
    doctor_mail character varying(255),
    doctor_name character varying(255),
    doctor_phone character varying(255)
);
    DROP TABLE public.doctor;
       public         heap    postgres    false            �            1259    41324    doctor_doctor_id_seq    SEQUENCE     �   ALTER TABLE public.doctor ALTER COLUMN doctor_id ADD GENERATED BY DEFAULT AS IDENTITY (
    SEQUENCE NAME public.doctor_doctor_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);
            public          postgres    false    210            �            1259    41336    vaccine    TABLE     �   CREATE TABLE public.vaccine (
    vaccine_id bigint NOT NULL,
    vaccine_code character varying(255),
    vaccine_name character varying(255),
    protection_fnsh_date date,
    protection_strt_date date
);
    DROP TABLE public.vaccine;
       public         heap    postgres    false            �            1259    41334    vaccine_vaccine_id_seq    SEQUENCE     �   ALTER TABLE public.vaccine ALTER COLUMN vaccine_id ADD GENERATED BY DEFAULT AS IDENTITY (
    SEQUENCE NAME public.vaccine_vaccine_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);
            public          postgres    false    212            �          0    41289    animal 
   TABLE DATA           t   COPY public.animal (animal_id, breed, colour, date_of_birth, gender, animal_name, species, customer_id) FROM stdin;
    public          postgres    false    201   �3       �          0    41297    animal_vaccine 
   TABLE DATA           ?   COPY public.animal_vaccine (animal_id, vaccine_id) FROM stdin;
    public          postgres    false    202   �4       �          0    41302    appointment 
   TABLE DATA           ]   COPY public.appointment (appointment_id, appointment_date, doctor_id, animal_id) FROM stdin;
    public          postgres    false    204   �4       �          0    41309    available_date 
   TABLE DATA           V   COPY public.available_date (available_date_id, available_date, doctor_id) FROM stdin;
    public          postgres    false    206   5       �          0    41316    customer 
   TABLE DATA           ~   COPY public.customer (customer_id, customer_address, customer_city, customer_mail, customer_name, customer_phone) FROM stdin;
    public          postgres    false    208   a5       �          0    41326    doctor 
   TABLE DATA           p   COPY public.doctor (doctor_id, doctor_address, doctor_city, doctor_mail, doctor_name, doctor_phone) FROM stdin;
    public          postgres    false    210   76       �          0    41336    vaccine 
   TABLE DATA           u   COPY public.vaccine (vaccine_id, vaccine_code, vaccine_name, protection_fnsh_date, protection_strt_date) FROM stdin;
    public          postgres    false    212   7       �           0    0    animal_animal_id_seq    SEQUENCE SET     B   SELECT pg_catalog.setval('public.animal_animal_id_seq', 7, true);
          public          postgres    false    200            �           0    0    appointment_appointment_id_seq    SEQUENCE SET     L   SELECT pg_catalog.setval('public.appointment_appointment_id_seq', 6, true);
          public          postgres    false    203            �           0    0 $   available_date_available_date_id_seq    SEQUENCE SET     S   SELECT pg_catalog.setval('public.available_date_available_date_id_seq', 11, true);
          public          postgres    false    205            �           0    0    customer_customer_id_seq    SEQUENCE SET     F   SELECT pg_catalog.setval('public.customer_customer_id_seq', 6, true);
          public          postgres    false    207            �           0    0    doctor_doctor_id_seq    SEQUENCE SET     B   SELECT pg_catalog.setval('public.doctor_doctor_id_seq', 7, true);
          public          postgres    false    209            �           0    0    vaccine_vaccine_id_seq    SEQUENCE SET     D   SELECT pg_catalog.setval('public.vaccine_vaccine_id_seq', 7, true);
          public          postgres    false    211            I           2606    41296    animal animal_pkey 
   CONSTRAINT     W   ALTER TABLE ONLY public.animal
    ADD CONSTRAINT animal_pkey PRIMARY KEY (animal_id);
 <   ALTER TABLE ONLY public.animal DROP CONSTRAINT animal_pkey;
       public            postgres    false    201            K           2606    41306    appointment appointment_pkey 
   CONSTRAINT     f   ALTER TABLE ONLY public.appointment
    ADD CONSTRAINT appointment_pkey PRIMARY KEY (appointment_id);
 F   ALTER TABLE ONLY public.appointment DROP CONSTRAINT appointment_pkey;
       public            postgres    false    204            M           2606    41313 "   available_date available_date_pkey 
   CONSTRAINT     o   ALTER TABLE ONLY public.available_date
    ADD CONSTRAINT available_date_pkey PRIMARY KEY (available_date_id);
 L   ALTER TABLE ONLY public.available_date DROP CONSTRAINT available_date_pkey;
       public            postgres    false    206            O           2606    41323    customer customer_pkey 
   CONSTRAINT     ]   ALTER TABLE ONLY public.customer
    ADD CONSTRAINT customer_pkey PRIMARY KEY (customer_id);
 @   ALTER TABLE ONLY public.customer DROP CONSTRAINT customer_pkey;
       public            postgres    false    208            Q           2606    41333    doctor doctor_pkey 
   CONSTRAINT     W   ALTER TABLE ONLY public.doctor
    ADD CONSTRAINT doctor_pkey PRIMARY KEY (doctor_id);
 <   ALTER TABLE ONLY public.doctor DROP CONSTRAINT doctor_pkey;
       public            postgres    false    210            S           2606    41343    vaccine vaccine_pkey 
   CONSTRAINT     Z   ALTER TABLE ONLY public.vaccine
    ADD CONSTRAINT vaccine_pkey PRIMARY KEY (vaccine_id);
 >   ALTER TABLE ONLY public.vaccine DROP CONSTRAINT vaccine_pkey;
       public            postgres    false    212            X           2606    49333 '   appointment fk2kkeptdxfuextg5ch7xp3ytie    FK CONSTRAINT     �   ALTER TABLE ONLY public.appointment
    ADD CONSTRAINT fk2kkeptdxfuextg5ch7xp3ytie FOREIGN KEY (animal_id) REFERENCES public.animal(animal_id);
 Q   ALTER TABLE ONLY public.appointment DROP CONSTRAINT fk2kkeptdxfuextg5ch7xp3ytie;
       public          postgres    false    201    2889    204            V           2606    41354 *   animal_vaccine fk4t45focpxcnxbcwmljt94lw4u    FK CONSTRAINT     �   ALTER TABLE ONLY public.animal_vaccine
    ADD CONSTRAINT fk4t45focpxcnxbcwmljt94lw4u FOREIGN KEY (animal_id) REFERENCES public.animal(animal_id);
 T   ALTER TABLE ONLY public.animal_vaccine DROP CONSTRAINT fk4t45focpxcnxbcwmljt94lw4u;
       public          postgres    false    201    202    2889            T           2606    41344 "   animal fk6pvxm5gfjqxclb651be9unswe    FK CONSTRAINT     �   ALTER TABLE ONLY public.animal
    ADD CONSTRAINT fk6pvxm5gfjqxclb651be9unswe FOREIGN KEY (customer_id) REFERENCES public.customer(customer_id);
 L   ALTER TABLE ONLY public.animal DROP CONSTRAINT fk6pvxm5gfjqxclb651be9unswe;
       public          postgres    false    2895    208    201            Y           2606    41364 *   available_date fkk0d6pu1wxarsoou0x2e1cc2on    FK CONSTRAINT     �   ALTER TABLE ONLY public.available_date
    ADD CONSTRAINT fkk0d6pu1wxarsoou0x2e1cc2on FOREIGN KEY (doctor_id) REFERENCES public.doctor(doctor_id);
 T   ALTER TABLE ONLY public.available_date DROP CONSTRAINT fkk0d6pu1wxarsoou0x2e1cc2on;
       public          postgres    false    2897    210    206            W           2606    41359 '   appointment fkoeb98n82eph1dx43v3y2bcmsl    FK CONSTRAINT     �   ALTER TABLE ONLY public.appointment
    ADD CONSTRAINT fkoeb98n82eph1dx43v3y2bcmsl FOREIGN KEY (doctor_id) REFERENCES public.doctor(doctor_id);
 Q   ALTER TABLE ONLY public.appointment DROP CONSTRAINT fkoeb98n82eph1dx43v3y2bcmsl;
       public          postgres    false    2897    204    210            U           2606    41349 *   animal_vaccine fkposofik58tg49f36pwlv9h9qo    FK CONSTRAINT     �   ALTER TABLE ONLY public.animal_vaccine
    ADD CONSTRAINT fkposofik58tg49f36pwlv9h9qo FOREIGN KEY (vaccine_id) REFERENCES public.vaccine(vaccine_id);
 T   ALTER TABLE ONLY public.animal_vaccine DROP CONSTRAINT fkposofik58tg49f36pwlv9h9qo;
       public          postgres    false    2899    202    212            �   �   x�}�A�0E�3��5m��V4�э�t�@��IiTno[@1&&�Y4�u�g�7U���^Y��]��8eB���J���us������l��[[id�8F�mu{���n`�td�+i݉Ec�0K�?^�e���*Y\ɨ$�&��>��[��8�>
Ե�cL`J¼;*]�%<����^�      �   (   x��A 0��w+fw� 3�cI&iQ^�M;�����D�      �   G   x�U̱�@C�ڞ�@�����\�"���d��|�y]f3�h2���D�K X���%��ɷ9Q��&�      �   @   x�M��	  �s�%Y���C��\�a(TzR�	Q>��vX�R�.y^G���f�3XB%      �   �   x��б�0���+�,8�������4�`��1:�����_�FE�q��'���`��Zw�nX&Y��\J�yX	@���P���BB �Աisp�PزZ�zȫ	+��7�kcZ!��R���	?2�F�c�	�.�`��lZ�b�o�z�G�O�?>aa�1����.��xtsX�N7��T�����`��_�lB�ES��      �   �   x���=�@F��SpB��0V$�&6�Nd���R�g1r9�˽$j������M �����`_�F�7������pϥ�dL��\l������p0T��v��U<�	ݏ�s���x� BB�v������
W�t�����x?d���%��o�|�������g�����)m+�ŁU$Wd6&Ύԕ���e�} XO��      �   }   x�3�
3202�JL�L-VKLN��K����X���&&��jT��Fp�`&�1gp XmpAjrIjN�� �&�)7�t+w�/��K���j3�jS�T�CT�aQm�Pm�k`
R���� ��Ln     